package com.gamebuy.store.service;

import com.gamebuy.store.dao.UserDAO;
import com.gamebuy.store.domain.Role;
import com.gamebuy.store.domain.User;

public class LoginService {

    private static LoginService instance;
    private static int loggedInUserId = -1;
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

    public boolean authenticate(String username, String password) {
        User user = userDAO.getUserByUsername(username);
        int id = user.getId();
        boolean isPasswordCorrect = user.getPassword().equals(password);
        if (isPasswordCorrect) {
            setLoggedInUserId(id);
        }
        return isPasswordCorrect;
    }

    public void setLoggedInUserId(int id) {
        loggedInUserId = id;
    }

    public boolean isUserLoggedIn() {
        return !(loggedInUserId == -1);
    }

    public void logOutUser() {
        loggedInUserId = -1;
    }

    public boolean checkRoleOfCurrentUser(Role role) {
        return userDAO
                .getUser(loggedInUserId)
                .getRole()
                .equals(role);
    }


}
