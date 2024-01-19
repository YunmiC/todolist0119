package com.teamspareta.todoapplication.domain.todo.dto


data class CreateTodoRequest(
   val title: String,
   val content: String,
   val name: String,
   val status: Boolean = false
)
