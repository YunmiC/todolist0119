package com.teamspareta.todoapplication.domain.todo.repository

import com.teamsparta.newsfeed.domain.article.model.Article
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Article, Long>