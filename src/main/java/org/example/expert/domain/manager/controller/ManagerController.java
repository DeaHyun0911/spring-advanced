package org.example.expert.domain.manager.controller;

import java.util.List;

import org.example.expert.config.JwtUtil;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.manager.dto.request.ManagerSaveRequestDto;
import org.example.expert.domain.manager.dto.response.ManagerResponseDto;
import org.example.expert.domain.manager.dto.response.ManagerSaveResponseDto;
import org.example.expert.domain.manager.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ManagerController {

	private final ManagerService managerService;
	private final JwtUtil jwtUtil;

	@PostMapping("/todos/{todoId}/managers")
	public ResponseEntity<ManagerSaveResponseDto> saveManager(
		@Auth AuthUser authUser,
		@PathVariable long todoId,
		@Valid @RequestBody ManagerSaveRequestDto dto
	) {
		return ResponseEntity.ok(managerService.saveManager(authUser, todoId, dto));
	}

	@GetMapping("/todos/{todoId}/managers")
	public ResponseEntity<List<ManagerResponseDto>> findMembers(@PathVariable long todoId) {
		return ResponseEntity.ok(managerService.findManagers(todoId));
	}

	@DeleteMapping("/todos/{todoId}/managers/{managerId}")
	public void deleteManager(
		@RequestHeader("Authorization") String bearerToken,
		@PathVariable long todoId,
		@PathVariable long managerId
	) {
		Claims claims = jwtUtil.extractClaims(bearerToken.substring(7));
		long userId = Long.parseLong(claims.getSubject());
		managerService.deleteManager(userId, todoId, managerId);
	}
}
