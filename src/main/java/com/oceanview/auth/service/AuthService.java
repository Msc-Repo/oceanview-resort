package com.oceanview.auth.service;

import com.oceanview.auth.model.User;

import java.sql.SQLException;
import java.util.Optional;

public interface AuthService {

    Optional<User> login(String username, String password) throws SQLException;

}