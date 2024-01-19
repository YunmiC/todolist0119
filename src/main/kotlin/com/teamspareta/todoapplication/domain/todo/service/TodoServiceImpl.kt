package com.teamspareta.todoapplication.domain.todo.service

import com.teamsparta.newsfeed.domain.article.dto.ArticleResponse
import com.teamsparta.newsfeed.domain.article.dto.CreateArticleRequest
import com.teamsparta.newsfeed.domain.article.dto.RetrieveTodoResponse
import com.teamsparta.newsfeed.domain.article.dto.UpdateArticleRequest
import com.teamsparta.newsfeed.domain.article.model.Article
import com.teamsparta.newsfeed.domain.article.model.toResponse
import com.teamsparta.newsfeed.domain.article.repository.ArticleRepository
import com.teamsparta.newsfeed.domain.exception.ArticleNotFoundException
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
        private val articleRepository: ArticleRepository,
) : TodoService {
    override fun getArticleList(pageable: Pageable): Page<ArticleResponse> {
        return articleRepository.findAll(pageable).map { it.toResponse() }
    }

    override fun getArticleById(articleId: Long): RetrieveTodoResponse {
        val getArticle = articleRepository.findByIdOrNull(articleId)
                ?: throw ArticleNotFoundException("Article", articleId)

        return getArticle.let { RetrieveTodoResponse.from(it) }
    }

    @Transactional
    override fun createArticle(request: CreateArticleRequest): ArticleResponse {
        return articleRepository.save<Article>(
                Article(
                        title = request.title,
                        summary = request.summary,
                        tag = request.tag,
                        content = request.content,
                        name = request.name,
                )
        ).toResponse()
    }

    @Transactional
    override fun updateArticle(articleId: Long, request: UpdateArticleRequest): ArticleResponse {

        val article = articleRepository.findByIdOrNull(articleId)
                ?: throw ArticleNotFoundException("Article", articleId)
        article.updateArticle(request)
        return article.toResponse()
    }

    @Transactional
    override fun deleteArticle(articleId: Long) {
        val deleteArticle = articleRepository.findByIdOrNull(articleId)
                ?: throw ArticleNotFoundException("Article", articleId)
        articleRepository.delete(deleteArticle)

    }

}