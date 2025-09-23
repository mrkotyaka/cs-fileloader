package ru.mrkotyaka.csfileloader.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.mrkotyaka.csfileloader.service.AuthService;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private final AuthService authService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String token = request.getHeader("auth-token");

        if (token != null && !request.getRequestURI().equals("/login")) {
            try {
                var user = authService.validateToken(token);
                var authentication = new UsernamePasswordAuthenticationToken(
                        user, null, Collections.singletonList(new SimpleGrantedAuthority("USER")));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (RuntimeException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
