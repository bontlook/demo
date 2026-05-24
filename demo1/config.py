import os

BASE_DIR = os.path.abspath(os.path.dirname(__file__))

SQLALCHEMY_DATABASE_URI = f"sqlite:///{os.path.join(BASE_DIR, 'newsradar.db')}"
SQLALCHEMY_TRACK_MODIFICATIONS = False

SECRET_KEY = os.environ.get("SECRET_KEY", "newsradar-dev-key")

SCHEDULER_INTERVAL_MINUTES = 30

NEWS_SOURCES = [
    {
        "name": "黑客新闻",
        "url": "https://hnrss.org/frontpage",
        "source_type": "rss",
        "category": "tech",
    },
    {
        "name": "BBC新闻",
        "url": "https://feeds.bbci.co.uk/news/rss.xml",
        "source_type": "rss",
        "category": "world",
    },
    {
        "name": "路透社",
        "url": "https://www.reutersagency.com/feed/?taxonomy=best-sectors&post_type=best",
        "source_type": "rss",
        "category": "world",
    },
    {
        "name": "新浪新闻",
        "url": "https://news.sina.com.cn/",
        "source_type": "web",
        "category": "china",
    },
    {
        "name": "网易新闻",
        "url": "https://news.163.com/",
        "source_type": "web",
        "category": "china",
    },
]
