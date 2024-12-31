package org.example.expert.domain.todo.dto.response;

import java.time.LocalDateTime;

import org.example.expert.domain.user.dto.response.UserResponse;

public record TodoResponse(Long id,
						   String title,
						   String contents,
						   String weather,
						   UserResponse user,
						   LocalDateTime createdAt,
						   LocalDateTime modifiedAt) {

}
