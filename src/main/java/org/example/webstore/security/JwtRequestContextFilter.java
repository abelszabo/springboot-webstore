package org.example.webstore.security;

import org.example.webstore.base.RequestContext;
import org.example.webstore.base.RequestContextHolder;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

public class JwtRequestContextFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        try {
            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            if (authentication != null
                    && authentication.isAuthenticated()
                    && authentication.getPrincipal() instanceof Jwt jwt) {

                UUID userUuid = UUID.fromString(
                        jwt.getClaimAsString("user_uuid")
                );

                RequestContextHolder.set(
                        new RequestContext(userUuid, jwt.getSubject())
                );
            }

            filterChain.doFilter(request, response);

        } finally {
            RequestContextHolder.clear();
        }
    }
}
