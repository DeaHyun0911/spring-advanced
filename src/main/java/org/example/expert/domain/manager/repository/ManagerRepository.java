package org.example.expert.domain.manager.repository;

import java.util.List;

import org.example.expert.domain.manager.entity.Manager;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

	default Manager findByIdOrElseThrow(Long id) {
		return findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
	}

	@EntityGraph(attributePaths = {"user"})
	List<Manager> findAllByTodoId(Long todoId);

	boolean existsByTodoIdAndUserId(Long todoId, Long userId);
}
