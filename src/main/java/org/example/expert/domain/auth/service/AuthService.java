package org.example.expert.domain.auth.service;

import org.example.expert.config.JwtUtil;
import org.example.expert.config.PasswordEncoder;
import org.example.expert.domain.auth.dto.request.SigninRequestDto;
import org.example.expert.domain.auth.dto.request.SignupRequestDto;
import org.example.expert.domain.auth.dto.response.SigninResponseDto;
import org.example.expert.domain.auth.dto.response.SignupResponseDto;
import org.example.expert.domain.auth.exception.AuthException;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.enums.UserRole;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtUtil jwtUtil;

	@Transactional
	public SignupResponseDto signup(SignupRequestDto dto) {

		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new InvalidRequestException("이미 존재하는 이메일입니다.");
		}

		String encodedPassword = passwordEncoder.encode(dto.getPassword());

		UserRole userRole = UserRole.of(dto.getUserRole());

		User newUser = new User(
			dto.getEmail(),
			encodedPassword,
			userRole
		);
		User savedUser = userRepository.save(newUser);

		String bearerToken = jwtUtil.createToken(savedUser.getId(), savedUser.getEmail(), userRole);

		return new SignupResponseDto(bearerToken);
	}

	public SigninResponseDto signin(SigninRequestDto dto) {
		User user = userRepository.findByEmail(dto.getEmail()).orElseThrow(
			() -> new InvalidRequestException("가입되지 않은 유저입니다."));

		if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
			throw new AuthException("잘못된 비밀번호입니다.");
		}

		String bearerToken = jwtUtil.createToken(user.getId(), user.getEmail(), user.getUserRole());

		return new SigninResponseDto(bearerToken);
	}
}
