from datetime import datetime
from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()


class NewsSource(db.Model):
    __tablename__ = "news_sources"

    id = db.Column(db.Integer, primary_key=True)
    name = db.Column(db.String(100), nullable=False)
    url = db.Column(db.String(500), nullable=False)
    source_type = db.Column(db.String(20), nullable=False)
    category = db.Column(db.String(50), nullable=False)
    enabled = db.Column(db.Boolean, default=True)

    articles = db.relationship("NewsArticle", backref="source", lazy="dynamic")


class NewsArticle(db.Model):
    __tablename__ = "news_articles"

    id = db.Column(db.Integer, primary_key=True)
    title = db.Column(db.String(300), nullable=False)
    url = db.Column(db.String(500), unique=True, nullable=False)
    summary = db.Column(db.Text, default="")
    source_id = db.Column(db.Integer, db.ForeignKey("news_sources.id"), nullable=False)
    category = db.Column(db.String(50), nullable=False)
    published_at = db.Column(db.DateTime, default=datetime.utcnow)
    created_at = db.Column(db.DateTime, default=datetime.utcnow)
