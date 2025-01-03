package org.example.expert.config;

import java.time.LocalDateTime;

import org.example.expert.domain.user.enums.UserRole;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class AdminCheckInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
		Exception {

		LocalDateTime requestTime = LocalDateTime.now();
		String requestURI = request.getRequestURI();
		String requestURL = request.getRequestURL().toString();

		Object userRole = request.getAttribute("userRole");
		UserRole userRoleEnum = UserRole.valueOf(userRole.toString());

		if (userRoleEnum == null) {
			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "사용자 역할이 없습니다.");
			return false;
		}

		if (!userRoleEnum.equals(UserRole.ADMIN)) {
			log.info("관리자 권한이 없는 사용자 요청");
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "관리자 권한이 없습니다.");
			return false;
		}

		log.info("requestURL = {}, requestTime = {}", requestURL, requestTime);
		return true;
	}
}
