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

    /**
     * Sets the current logged in user.
     *
     * @param id user being logged in.
     */
    public void setLoggedInUserId(int id) {
        loggedInUserId = id;
    }

    /**
     * Returns whether a user is logged in.
     *
     * @return boolean
     */
    public boolean isUserLoggedIn() {
        return !(loggedInUserId == -1);
    }

    /**
     * Logs out the current user.
     */
    public void logOutUser() {
        loggedInUserId = -1;
    }

    /**
     * Checks whether logged in user is of specified role (admin or customer).
     *
     * @param role
     * @return is specified role equal to current logged in user's role.
     */
    public boolean checkRoleOfCurrentUser(Role role) {
        if (loggedInUserId == -1) {
            return false;
        }
        return userDAO
                .getUserById(loggedInUserId)
                .getRole()
                .equals(role);
    }

    /**
     * Encrypts password to be stored in database using MD5 hash function.
     *
     * @param in string to encrypt
     * @return encrypted hash string
     */
    public String getMd5Hash(String in) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(in.getBytes());

            BigInteger bigInteger = new BigInteger(1, messageDigest);

            String hash = bigInteger.toString(16);

            while (hash.length() < 32) {
                hash = "0" + hash;
            }
            return hash;
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
}
