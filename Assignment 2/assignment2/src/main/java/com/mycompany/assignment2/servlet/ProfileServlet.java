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

@WebServlet(name = "ProfileServlet", urlPatterns = {"/profile"})
public class ProfileServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("update".equals(action)) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user != null) {
                user.setUsername(request.getParameter("username"));
                user.setEmail(request.getParameter("email"));
                user.setPhone(request.getParameter("phone"));
                user.setAddress(request.getParameter("address"));
                user.setCity(request.getParameter("city"));
                user.setState(request.getParameter("state"));
                user.setCountry(request.getParameter("country"));
                user.setPin(request.getParameter("pin"));
                
                if (userDAO.updateUser(user)) {
                    session.setAttribute("user", user); // Update session
                    response.sendRedirect("profile.jsp?msg=Profile Updated");
                } else {
                    response.sendRedirect("profile.jsp?error=Update Failed");
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        }
    }
}
