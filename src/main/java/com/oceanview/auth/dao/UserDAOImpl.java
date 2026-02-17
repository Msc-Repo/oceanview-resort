package com.oceanview.auth.dao;

import com.oceanview.auth.model.User;
import com.oceanview.common.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {

    private static final String SQL =
            "SELECT id, username, role FROM users WHERE username = ? AND password_hash = ?";

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String passwordHash) throws SQLException {

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL)) {

            statement.setString(1, username);
            statement.setString(2, passwordHash);

            try (ResultSet rs = statement.executeQuery()) {

                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setRole(rs.getString("role"));

                    return Optional.of(user);
                }
            }
        }

        return Optional.empty();
    }
}
