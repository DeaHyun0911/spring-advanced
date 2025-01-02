package org.example.expert.domain.todo.repository;

import java.util.NoSuchElementException;

import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

	default Todo findByIdOrElseThrow(Long id) {
		return findById(id).orElseThrow(() -> new InvalidRequestException("Todo not found"));
	}

	int countById(Long todoId);
}
