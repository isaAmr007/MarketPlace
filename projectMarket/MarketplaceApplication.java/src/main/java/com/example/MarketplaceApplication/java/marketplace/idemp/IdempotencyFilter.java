package com.example.MarketplaceApplication.java.marketplace.idemp;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class IdempotencyFilter implements Filter {

    private Set<String> requestIds = new HashSet<>();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        String idempotencyKey = httpRequest.getHeader("Idempotency-Key");

        if (idempotencyKey != null) {
            synchronized (this) {
                if (requestIds.contains(idempotencyKey)) {
                    throw new ServletException("Duplicate request");
                }
                requestIds.add(idempotencyKey);
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}

