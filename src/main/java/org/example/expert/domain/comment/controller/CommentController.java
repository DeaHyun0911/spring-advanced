package org.example.expert.domain.comment.controller;

import java.util.List;

import org.example.expert.domain.comment.dto.request.CommentSaveRequestDto;
import org.example.expert.domain.comment.dto.response.CommentResponseDto;
import org.example.expert.domain.comment.dto.response.CommentSaveResponseDto;
import org.example.expert.domain.comment.service.CommentService;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CommentController {

	private final CommentService commentService;

	@PostMapping("/todos/{todoId}/comments")
	public ResponseEntity<CommentSaveResponseDto> saveComment(
		@Auth AuthUser authUser,
		@PathVariable long todoId,
		@Valid @RequestBody CommentSaveRequestDto dto
	) {
		return ResponseEntity.ok(commentService.saveComment(authUser, todoId, dto));
	}

	@GetMapping("/todos/{todoId}/comments")
	public ResponseEntity<List<CommentResponseDto>> findComments(@PathVariable long todoId) {
		return ResponseEntity.ok(commentService.findComments(todoId));
	}
}
