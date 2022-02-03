package com.poscoict.mysite.interceptor;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.poscoict.mysite.security.Auth;

@Auth(role = "ADMIN")
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. AuthUser.getRole == "ADMIN"인지 검사
		// 2. 맞으면 return true;
		// 3. 틀리면 return false;
		System.out.println("AuthInterceptor.preHandle(...) called");
		return true;
	}
}