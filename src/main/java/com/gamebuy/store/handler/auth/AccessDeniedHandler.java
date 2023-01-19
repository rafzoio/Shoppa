package com.gamebuy.store.handler.auth;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AccessDeniedHandler implements HttpHandler {

    /**
     * Handles the access denied page.
     * If a certain user does not have permission to use a page, they will be redirected here.
     *
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("AccessDeniedHandler called");

        exchange.sendResponseHeaders(200, 0);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody()));

        out.write(
                "<html>" +
                        "<head> <title>Access Denied</title> " +
                        "<meta charset=\"utf-8\">" +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>Access Denied</h1>" +
                        "<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/menu\">Return to Menu</a></button> " +
                        "<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/auth/logout\">Log Out</a></button>" +
                        "</body>" +
                        "</html>"
        );
        out.close();

    }
}
