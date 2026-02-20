package ir.zohre.security.chapter5;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AuthenticationLoggingFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationLoggingFilter.class);


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestId = request.getHeader("Request-Id");
        logger.info("info(\"Successfully authenticated #B\n" +
                "request with id: " + requestId);

        filterChain.doFilter(request, response);
    }
}
