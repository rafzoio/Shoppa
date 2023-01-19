package com.gamebuy.store.handler.auth;

import com.gamebuy.store.service.LoginService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class LogOutHandler implements HttpHandler {

    /**
     * Handles user log out. Redirects to RootHandler.
     *
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("LogOutHandler called");

        LoginService loginService = LoginService.getInstance();

        loginService.logOutUser();

        exchange.getResponseHeaders().add("Location", "http://localhost:8090/");
        exchange.sendResponseHeaders(307,0);

    }
}
