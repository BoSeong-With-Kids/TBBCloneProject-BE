package com.team1.TBBCloneCoding.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;


import com.team1.TBBCloneCoding.common.dto.ResponseDto;
import com.team1.TBBCloneCoding.common.exception.ExceptionMessage;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.team1.TBBCloneCoding.common.exception.ExceptionMessage.TOKEN_ERROR_MSG;
import static com.team1.TBBCloneCoding.common.exception.ExceptionMessage.USER_DOES_NOT_EXIST_ERROR_MSG;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);

        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        if (!jwtUtil.validateToken(token)) {
            jwtExceptionHandler(response, TOKEN_ERROR_MSG);
            return;
        }
        Claims info = jwtUtil.getUserInfoFromToken(token);
        setAuthentication(response, info.getSubject());
        filterChain.doFilter(request, response);
    }
    public void setAuthentication(HttpServletResponse response,String username) {
        try {
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authentication = jwtUtil.createAuthentication(username);
            context.setAuthentication(authentication);

            SecurityContextHolder.setContext(context);
        } catch (UsernameNotFoundException e) {
            jwtExceptionHandler(response, USER_DOES_NOT_EXIST_ERROR_MSG);
        }
    }

    public void jwtExceptionHandler(HttpServletResponse response, ExceptionMessage exceptionMessage) {
        response.setStatus(exceptionMessage.getStatus());
        response.setContentType("application/json; charset=utf8");
        try {
            String json = new ObjectMapper().writeValueAsString(new ResponseDto("fail", exceptionMessage.getMsg(), null));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}
