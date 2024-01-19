package com.teamspareta.todoapplication.domain.todo.service

import com.teamsparta.newsfeed.domain.article.dto.RetrieveTodoResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface TodoService {
    fun getArticleList(pageable: Pageable): Page<TodoResponse>

    fun getArticleById(articleId: Long): RetrieveTodoResponse

    fun createArticle(request: CreateTodoRequest): TodoResponse

    fun updateArticle(articleId: Long, request: UpdateTodoRequest): TodoResponse

    fun deleteArticle(articleId: Long)
}