package com.teamspareta.todoapplication.domain.todo.model

import com.teamspareta.todoapplication.domain.BaseTimeEntity
import com.teamspareta.todoapplication.domain.todo.dto.TodoResponse
import com.teamspareta.todoapplication.domain.todo.dto.UpdateTodoRequest
import com.teamspareta.todoapplication.domain.comment.model.Comment
import jakarta.persistence.*


@Entity
@Table(name = "articles")
class Article(
        @Column(name = "title", nullable = false)
        var title: String,

        @Column(name = "summary", nullable = false)
        var summary: String,

        @Column(name = "tag", nullable = false)
        var tag: String,

        @Column(name = "content", nullable = false)
        var content: String,

        @Column(name = "name", nullable = false)
        var name: String,

        @OneToMany(mappedBy = "article", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
        val comments: MutableList<Comment> = mutableListOf(),

) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


    fun updateArticle(request: UpdateArticleRequest) {
        title = request.title
        summary = request.summary
        tag = request.tag
        content = request.content
        name = request.name
    }
}

fun Article.toResponse(): ArticleResponse {
    return ArticleResponse(
            id = id!!,
            title = title,
            summary = summary,
            tag = tag,
            content = content,
            name = name
    )
}
