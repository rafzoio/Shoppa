package com.gamebuy.store.service;

import com.gamebuy.store.dao.UserDAO;
import com.gamebuy.store.domain.Role;
import com.gamebuy.store.domain.User;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    /**
     * Authenticates a username and password combination.
     * If correct, the loggedInUserId will be set to the userId.
     *
     * @param username
     * @param password
     * @return whether or not password combination is correct.
     */
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
        if (loggedInUserId == -1) {
            return false;
        }
        return userDAO
                .getUserById(loggedInUserId)
                .getRole()
                .equals(role);
    }

    public String getMd5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);

            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
