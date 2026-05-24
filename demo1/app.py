import logging

from flask import Flask, render_template, request, redirect, url_for, flash
from apscheduler.schedulers.background import BackgroundScheduler

import config
from models import db, NewsSource, NewsArticle
from scraper.rss_scraper import fetch_rss_source
from scraper.web_scraper import fetch_web_source

logging.basicConfig(
    level=logging.INFO,
    format="%(asctime)s [%(levelname)s] %(message)s",
)
logger = logging.getLogger(__name__)

app = Flask(__name__)
app.config.from_object(config)

db.init_app(app)


def init_sources():
    for src in config.NEWS_SOURCES:
        exists = NewsSource.query.filter_by(url=src["url"]).first()
        if not exists:
            db.session.add(NewsSource(**src))
    db.session.commit()


def run_scrapers():
    with app.app_context():
        sources = NewsSource.query.filter_by(enabled=True).all()
        for source in sources:
            if source.source_type == "rss":
                fetch_rss_source(source)
            elif source.source_type == "web":
                fetch_web_source(source)
        logger.info("Scraping cycle complete.")


@app.route("/")
def index():
    category = request.args.get("category", "all")
    page = request.args.get("page", 1, type=int)

    query = NewsArticle.query.order_by(NewsArticle.published_at.desc())
    if category != "all":
        query = query.filter_by(category=category)

    pagination = query.paginate(page=page, per_page=20, error_out=False)
    return render_template(
        "index.html",
        articles=pagination.items,
        pagination=pagination,
        current_category=category,
    )


@app.route("/search")
def search():
    keyword = request.args.get("q", "").strip()
    page = request.args.get("page", 1, type=int)

    if not keyword:
        return redirect(url_for("index"))

    query = NewsArticle.query.filter(
        NewsArticle.title.ilike(f"%{keyword}%")
    ).order_by(NewsArticle.published_at.desc())

    pagination = query.paginate(page=page, per_page=20, error_out=False)
    return render_template(
        "index.html",
        articles=pagination.items,
        pagination=pagination,
        current_category="all",
        search_keyword=keyword,
    )


@app.route("/refresh", methods=["POST"])
def refresh():
    run_scrapers()
    flash("新闻刷新成功!", "success")
    return redirect(url_for("index"))


scheduler = BackgroundScheduler()
scheduler.add_job(
    run_scrapers,
    "interval",
    minutes=config.SCHEDULER_INTERVAL_MINUTES,
    id="news_scraper",
)


if __name__ == "__main__":
    with app.app_context():
        db.create_all()
        init_sources()

    scheduler.start()
    logger.info(
        f"Scheduler started (interval: {config.SCHEDULER_INTERVAL_MINUTES} min)"
    )

    try:
        app.run(debug=True, use_reloader=False, port=5000)
    finally:
        scheduler.shutdown()
