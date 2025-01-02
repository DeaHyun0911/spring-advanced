package org.example.expert.domain.user.repository;

import java.util.Optional;

import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	default User findByIdOrElseThrow(Long id) {
		return findById(id).orElseThrow(() -> new InvalidRequestException("User not found"));
	}

	Optional<User> findByEmail(String email);

	boolean existsByEmail(String email);
}
