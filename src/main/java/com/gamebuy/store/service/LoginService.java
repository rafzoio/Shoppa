package com.gamebuy.store.service;

import com.gamebuy.store.dao.UserDAO;
import com.gamebuy.store.domain.User;

public class LoginService {

    private static LoginService instance;

    private final UserDAO userDAO;

    private LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static LoginService getInstance() {
        UserDAO userDAO = new UserDAO();
        if (instance == null) {
            instance = new LoginService(userDAO);
        }
        return instance;
    }

    public boolean isAuthenticated(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        return user.getPassword().equals(password);
    }

}
