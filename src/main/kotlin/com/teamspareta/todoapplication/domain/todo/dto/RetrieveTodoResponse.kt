package com.teamspareta.todoapplication.domain.todo.dto

import com.teamspareta.todoapplication.domain.todo.model.Todo
import com.teamspareta.todoapplication.domain.comment.dto.CommentResponse
import com.teamspareta.todoapplication.domain.comment.model.toResponse
import com.teamspareta.todoapplication.domain.todo.dto.TodoResponse

class RetrieveTodoResponse(
        val id: Long?,
        val title: String,
//        val createDate: String, // 생성일자
//        val updateDate: String, // 수정일자
        val content: String,
        val name: String,
        val status : Boolean = false,
        val comments: List<CommentResponse>,
) {
    companion object {
        fun from(todo: Todo): RetrieveTodoResponse {
            return RetrieveTodoResponse(
                    id = todo.id!!,
                    title = todo.title,
                    content = todo.content,
//                    createDate = todo.createDate, // 생성일자
//                    updateDate = todo.updateDate, // 수정일자
                    name = todo.name,
                    comments = todo.comments.map { it.toResponse() }
            )
        }
    }
}
