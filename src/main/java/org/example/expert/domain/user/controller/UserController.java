package org.example.expert.domain.user.controller;

import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequestDto;
import org.example.expert.domain.user.dto.response.UserResponseDto;
import org.example.expert.domain.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping("/users/{userId}")
	public ResponseEntity<UserResponseDto> findUser(@PathVariable long userId) {
		return ResponseEntity.ok(userService.findUser(userId));
	}

	@PutMapping("/users")
	public void changePassword(@Auth AuthUser authUser,
							   @RequestBody UserChangePasswordRequestDto dto
	) {
		userService.changePassword(authUser.id(), dto);
	}
}
