package com.teamspareta.todoapplication.domain.todo.controller

import com.teamspareta.todoapplication.domain.todo.dto.TodoResponse
import com.teamspareta.todoapplication.domain.todo.dto.CreateTodoRequest
import com.teamspareta.todoapplication.domain.todo.dto.RetrieveTodoResponse
import com.teamspareta.todoapplication.domain.todo.dto.UpdateTodoRequest
import com.teamspareta.todoapplication.domain.todo.dto.RetrieveTodoResponse
import com.teamspareta.todoapplication.domain.todo.service.TodoService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RequestMapping("/todos")
@RestController
class TodoController(
        private val todoService: TodoService
) {
    @Operation(summary = "todo 전체 조회")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun getTodoList(
        @PageableDefault(size = 4, sort = ["name"], direction = Sort.Direction.DESC)
        pageable: Pageable
    ): ResponseEntity<Page<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoList(pageable))
    }

    @Operation(summary = "todo 단건 조회")
    @GetMapping("/{todoId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun getTodo(
            @PathVariable todoId: Long
    ): ResponseEntity<RetrieveTodoResponse?> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.getTodoById(todoId))
    }

    @Operation(summary = "todo 생성")
    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun createTodo(
            @RequestBody createTodoRequest: CreateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(todoService.createTodo(createTodoRequest))
    }

    @Operation(summary = "todo 수정")
    @PutMapping("/{todoId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun updateTodo(
            @PathVariable todoId: Long,
            @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(todoService.updateTodo(todoId, updateTodoRequest))
    }

    @Operation(summary = "todo 삭제")
    @DeleteMapping("/{todoId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun deleteTodo(
            @PathVariable todoId: Long,
    ): ResponseEntity<Any> {
        todoService.deleteTodo(todoId)
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("선택하신 항목이 삭제되었습니다.")
    }
}