package controllers

import models.Article
import play.api.mvc.Action
import play.api.mvc.Controller

object Articles extends Controller {
	def index = Action {
	  Ok(views.html.articles.list(Article.findAll))
	}
	
	def show(id: Long) = Action {
	  NotImplemented
	}
}