package com.mycompany.assignment2.filter;

import com.mycompany.assignment2.model.User;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthenticationFilter", urlPatterns = {"/admin/*", "/user/*", "/shop.jsp", "/cart.jsp", "/profile.jsp"})
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        HttpSession session = req.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute("user") != null);
        
        String reqURI = req.getRequestURI();
        
        if (isLoggedIn) {
             User user = (User) session.getAttribute("user");
             if (reqURI.contains("/admin/") && !"ADMIN".equals(user.getRole())) {
                 res.sendRedirect(req.getContextPath() + "/access_denied.jsp");
             } else {
                 chain.doFilter(request, response);
             }
        } else {
            // Check if requesting protected resources but not logged in
            // Allow public assets
            res.sendRedirect(req.getContextPath() + "/login.jsp");
        }
    }

    @Override
    public void destroy() {
    }
}
