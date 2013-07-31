package models

case class Article(id: Long, name: String)

object Article {
  var articles = Set(Article(1, "Paperclips Large"),
    Article(2, "Giant Paperclips"),
    Article(3, "Paperclip Giant Plain"),
    Article(4, "No Tear Paper Clip"),
    Article(5, "Zebra Paperclips"))

  def findAll = this.articles.toList.sortBy(_.name)
  def findById(id: Long) = this.articles.find(_.id == id)

  def add(articles: Article) = {
    this.articles = this.articles + articles
  }
}