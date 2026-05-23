import logging
from datetime import datetime

import feedparser

from models import db, NewsArticle

logger = logging.getLogger(__name__)


def fetch_rss_source(source):
    try:
        feed = feedparser.parse(source.url)
        count = 0
        for entry in feed.entries[:20]:
            url = entry.get("link", "")
            if not url:
                continue
            exists = db.session.query(
                NewsArticle.query.filter_by(url=url).exists()
            ).scalar()
            if exists:
                continue

            published = None
            if hasattr(entry, "published_parsed") and entry.published_parsed:
                published = datetime(*entry.published_parsed[:6])

            summary = entry.get("summary", "")
            if len(summary) > 200:
                summary = summary[:200] + "..."

            article = NewsArticle(
                title=entry.get("title", "Untitled"),
                url=url,
                summary=summary,
                source_id=source.id,
                category=source.category,
                published_at=published or datetime.utcnow(),
            )
            db.session.add(article)
            count += 1

        db.session.commit()
        logger.info(f"[RSS] {source.name}: fetched {count} new articles")
    except Exception as e:
        db.session.rollback()
        logger.error(f"[RSS] {source.name} failed: {e}")
