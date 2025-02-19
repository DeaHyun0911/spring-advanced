package org.example.expert.domain.todo.dto.response;

import java.time.LocalDateTime;

import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.dto.response.UserResponseDto;

public record TodoResponseDto(Long id,
							  String title,
							  String contents,
							  String weather,
							  UserResponseDto user,
							  LocalDateTime createdAt,
							  LocalDateTime modifiedAt) {

	public TodoResponseDto(Todo todo, UserResponseDto user) {
		this(todo.getId(),
			 todo.getTitle(),
			 todo.getContents(),
			 todo.getWeather(), user,
			 todo.getCreatedAt(),
			 todo.getModifiedAt()
		);
	}

}
