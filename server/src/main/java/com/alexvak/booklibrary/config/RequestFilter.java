package com.alexvak.booklibrary.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RequestFilter implements Filter {

    @Value("${access.control.allow.origin:*}")
    private String origin;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        res.setHeader("Access-Control-Allow-Origin", origin);
        res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        res.setHeader("Access-Control-Max-Age", "3600");
        res.setHeader("Access-Control-Allow-Headers", "x-requested-with, x-auth-token");
        res.setHeader("Access-Control-Allow-Credentials", "true");

        if (!req.getMethod().equalsIgnoreCase("OPTIONS")) {
            filterChain.doFilter(req, res);
        } else {
            res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE");
            res.setHeader("Access-Control-Max-Age", "3600");
            res.setHeader("Access-Control-Allow-Headers", "authorization, content-type, x-auth-token, " +
                    "access-control-request-headers, access-control-request-method, accept, origin, x-requested-with");
            res.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
