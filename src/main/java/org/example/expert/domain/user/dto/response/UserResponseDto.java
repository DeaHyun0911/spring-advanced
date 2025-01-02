package org.example.expert.domain.user.dto.response;

import org.example.expert.domain.manager.entity.Manager;
import org.example.expert.domain.user.entity.User;

public record UserResponseDto(Long id, String email) {
	public UserResponseDto(User user) {
		this(user.getId(), user.getEmail());
	}

}
