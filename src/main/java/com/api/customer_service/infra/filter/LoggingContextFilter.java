package com.api.customer_service.infra.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingContextFilter implements Filter {

    private static final String REQUEST_ID_HEADER = "X-Request-Id";
    private static final String CLIENT_ID_HEADER = "X-Client-Id";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        try {

            String requestId = request.getHeader(REQUEST_ID_HEADER);

            String clientId = request.getHeader(CLIENT_ID_HEADER);
            if (clientId == null) {
                clientId = "UNKNOWN";
            }

            MDC.put("requestId", requestId);
            MDC.put("clientId", clientId);

            filterChain.doFilter(servletRequest, servletResponse);

        } finally {

            MDC.clear();
        }
    }
}
