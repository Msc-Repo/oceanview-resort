package com.oceanview.common.factory;

import com.oceanview.auth.dao.UserDAO;
import com.oceanview.auth.dao.UserDAOImpl;
import com.oceanview.auth.service.AuthService;
import com.oceanview.auth.service.AuthServiceImpl;

public final class AppFactory {

    private AppFactory() {}

    public static UserDAO userDAO() {
        return new UserDAOImpl();
    }

    public static AuthService authService() {
        return new AuthServiceImpl(userDAO());
    }
}