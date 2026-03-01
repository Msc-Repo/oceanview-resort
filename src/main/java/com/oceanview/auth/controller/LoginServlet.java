package com.oceanview.auth.controller;

import com.oceanview.auth.model.User;
import com.oceanview.auth.service.AuthService;
import com.oceanview.common.factory.AppFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private AuthService authService;

    @Override
    public void init() {
        this.authService = AppFactory.authService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (username == null || username.isBlank() || password == null || password.isBlank()) {
            req.setAttribute("error", "Username and password are required.");
            req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
            return;
        }

        try {
            Optional<User> user = authService.login(username, password);

            if (user.isPresent()) {
                HttpSession session = req.getSession(true);
                session.setAttribute("user", user.get());
                resp.sendRedirect(req.getContextPath() + "/dashboard"); // dashboard next step
            } else {
                req.setAttribute("error", "Invalid username or password.");
                req.getRequestDispatcher("/WEB-INF/views/auth/login.jsp").forward(req, resp);
            }

        } catch (SQLException e) {
            throw new ServletException("Database error during login", e);
        }
    }
}