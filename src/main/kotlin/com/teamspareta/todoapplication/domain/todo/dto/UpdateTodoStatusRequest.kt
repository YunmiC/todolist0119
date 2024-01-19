package com.teamspareta.todoapplication.domain.todo.dto

data class UpdateTodoStatusRequest(

    val status: Boolean
)

// TodoService 클래스 내에서 업데이트 로직을 처리
fun updateTodoStatus(todoId: Long, updateRequest: UpdateTodoStatusRequest): Todo {
    val existingTodo = todoRepository.findById(todoId)
        .orElseThrow { NoSuchElementException("Todo not found") }

    // 업데이트 로직: status 변경
    existingTodo.status = updateRequest.status

    return todoRepository.save(existingTodo)
}

)
