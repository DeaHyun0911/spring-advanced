package org.example.expert.domain.comment.dto.response;

import org.example.expert.domain.user.dto.response.UserResponseDto;

public record CommentSaveResponseDto(Long id, String contents, UserResponseDto user) {

}
