package com.kode4you.getyourquote.spring.filter;

import com.kode4you.getyourquote.spring.service.UserService;
import com.kode4you.getyourquote.spring.utils.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends GenericFilterBean {

    @Autowired
    UserService userService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        Authentication authentication;

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "authorization, Authorization");
        response.addHeader("Access-Control-Expose-Headers", "*");
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        }

        try {
            authentication = TokenAuthenticationService
                    .getAuthentication((HttpServletRequest) servletRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("JWTAuthent<ticationFilter.doFilter");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
