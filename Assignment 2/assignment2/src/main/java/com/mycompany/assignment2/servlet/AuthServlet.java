package com.mycompany.assignment2.servlet;

import com.mycompany.assignment2.dao.UserDAO;
import com.mycompany.assignment2.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AuthServlet", urlPatterns = {"/auth"})
public class AuthServlet extends HttpServlet {
    
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("login".equals(action)) {
            login(request, response);
        } else if ("register".equals(action)) {
            register(request, response);
        } else if ("logout".equals(action)) {
            logout(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("logout".equals(action)) {
            logout(request, response);
        }
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String loginId = request.getParameter("loginId");
        String pass = request.getParameter("password");
        
        User user = userDAO.loginUser(loginId, pass);
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            
            // Check role and redirect
            if ("ADMIN".equals(user.getRole())) {
                response.sendRedirect("admin/dashboard.jsp");
            } else {
                response.sendRedirect("index.jsp"); // or shop.jsp
            }
        } else {
            request.setAttribute("error", "Invalid Credentials");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String captcha = (String) session.getAttribute("captcha");
        String userCaptcha = request.getParameter("captcha");
        
        if (captcha == null || !captcha.equals(userCaptcha)) {
            request.setAttribute("error", "Invalid Captcha");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        User user = new User();
        user.setUsername(request.getParameter("username"));
        user.setLoginId(request.getParameter("loginId"));
        user.setPassword(request.getParameter("password")); // Hash this in real world
        user.setPasswordQuestion(request.getParameter("passwordQuestion"));
        user.setPasswordAnswer(request.getParameter("passwordAnswer"));
        user.setEmail(request.getParameter("email"));
        user.setPhone(request.getParameter("phone"));
        user.setAddress(request.getParameter("address"));
        user.setCity(request.getParameter("city"));
        user.setState(request.getParameter("state"));
        user.setCountry(request.getParameter("country"));
        user.setPin(request.getParameter("pin"));
        
        if (userDAO.registerUser(user)) {
             response.sendRedirect("login.jsp?msg=Registered Successfully");
        } else {
            request.setAttribute("error", "Registration Failed");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect("login.jsp");
    }
}
