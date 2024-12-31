package org.example.expert.domain.comment.service;

import org.example.expert.domain.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentAdminService {

	private final CommentRepository commentRepository;

	@Transactional
	public void deleteComment(long commentId) {
		commentRepository.deleteById(commentId);
	}
}
