package org.example.expert.domain.todo.controller;

import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.todo.dto.request.TodoSaveRequestDto;
import org.example.expert.domain.todo.dto.response.TodoResponseDto;
import org.example.expert.domain.todo.dto.response.TodoSaveResponseDto;
import org.example.expert.domain.todo.service.TodoService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TodoController {

	private final TodoService todoService;

	@PostMapping("/todos")
	public ResponseEntity<TodoSaveResponseDto> saveTodo(@Auth AuthUser authUser,
														@Valid @RequestBody TodoSaveRequestDto dto
	) {
		return ResponseEntity.ok(todoService.saveTodo(authUser, dto));
	}

	@GetMapping("/todos")
	public ResponseEntity<Page<TodoResponseDto>> findTodos(@RequestParam(defaultValue = "1") int page,
														   @RequestParam(defaultValue = "10") int size
	) {
		return ResponseEntity.ok(todoService.findTodos(page, size));
	}

	@GetMapping("/todos/{todoId}")
	public ResponseEntity<TodoResponseDto> findTodo(@PathVariable long todoId) {
		return ResponseEntity.ok(todoService.findTodo(todoId));
	}
}
