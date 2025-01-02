package org.example.expert.domain.todo.repository;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TodoRepository extends JpaRepository<Todo, Long> {

	// @Query("SELECT t FROM Todo t JOIN FETCH t.user")
	@EntityGraph(attributePaths = {"user"})
	Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

	@EntityGraph(attributePaths = {"user"})
	Optional<Todo> findById(Long id);

	default Todo findByIdOrElseThrow(Long id) {
		return findById(id).orElseThrow(() -> new InvalidRequestException("Todo not found"));
	}

	int countById(Long todoId);
}
