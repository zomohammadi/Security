package ir.zohre.security.chapter5;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class StaticKeyAuthenticationFilter implements Filter {

    @Value("${authorization.key}")
    private  String authorizationKey;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        var httprequest = (HttpServletRequest) servletRequest;
        var httpresponse = (HttpServletResponse) servletResponse;
        String authorization = httprequest.getHeader("authorization");
        if (authorization.equals(authorizationKey)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else
            httpresponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
}
