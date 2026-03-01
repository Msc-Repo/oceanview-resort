package com.oceanview.auth.service;

import com.oceanview.auth.dao.UserDAO;
import com.oceanview.auth.model.User;
import com.oceanview.common.util.PasswordUtil;

import java.sql.SQLException;
import java.util.Optional;

public class AuthServiceImpl implements AuthService {

    private final UserDAO userDAO;

    public AuthServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> login(String username, String password) throws SQLException {

        if (username == null || username.isBlank()) {
            return Optional.empty();
        }

        if (password == null || password.isBlank()) {
            return Optional.empty();
        }

        String hashedPassword = PasswordUtil.hash(password);

        return userDAO.findByUsernameAndPassword(username.trim(), hashedPassword);
    }
}