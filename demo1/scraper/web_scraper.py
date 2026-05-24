import logging
from datetime import datetime

import requests
from bs4 import BeautifulSoup

from models import db, NewsArticle

logger = logging.getLogger(__name__)

HEADERS = {
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
    "(KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
}


def fetch_web_source(source):
    try:
        resp = requests.get(source.url, headers=HEADERS, timeout=15)
        resp.encoding = resp.apparent_encoding
        soup = BeautifulSoup(resp.text, "html.parser")

        articles = _extract_articles(soup, source)
        count = 0
        for title, url in articles:
            if not url or not title:
                continue
            if not url.startswith("http"):
                url = source.url.rstrip("/") + "/" + url.lstrip("/")

            exists = db.session.query(
                NewsArticle.query.filter_by(url=url).exists()
            ).scalar()
            if exists:
                continue

            article = NewsArticle(
                title=title.strip(),
                url=url,
                summary="",
                source_id=source.id,
                category=source.category,
                published_at=datetime.utcnow(),
            )
            db.session.add(article)
            count += 1

        db.session.commit()
        logger.info(f"[Web] {source.name}: fetched {count} new articles")
    except Exception as e:
        db.session.rollback()
        logger.error(f"[Web] {source.name} failed: {e}")


def _extract_articles(soup, source):
    results = []
    for a_tag in soup.find_all("a", href=True):
        title = a_tag.get_text(strip=True)
        href = a_tag["href"]
        if len(title) >= 8 and href.startswith("http"):
            results.append((title, href))
        if len(results) >= 30:
            break
    return results
