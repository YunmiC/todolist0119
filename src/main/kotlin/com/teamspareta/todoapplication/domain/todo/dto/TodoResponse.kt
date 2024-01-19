package com.teamspareta.todoapplication.domain.todo.dto


data class TodoResponse(
  val id :Long?,
  val title: String,
//  val createDate: String, // 생성일자
//  val updateDate: String, // 수정일자
  val content: String,
  val name: String,
  val status : Boolean = false,
)

