package com.gamebuy.store.handler.auth;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class LoginFormHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        exchange.sendResponseHeaders(200, 0);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody()));

        out.write(
                "<html>" +
                        "<head> " +
                        "<title>Login</title> " +
                        "<meta charset=\"utf-8\">" +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>Login</h1>" +
                        "<form method=\"post\" action=\"/auth/login\">" +
                        "<div class=\"form-group\"> "+

                        "<label for=\"username\">Username</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"username\" id=\"username\" required> " +

                        "<label for=\"password\">Password</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"password\" id=\"password\" required> " +

                        "</div>" +
                        "<button type=\"submit\" class=\"btn btn-primary\">Login</button> " +
                        "</form>" +
                        "<a href=\"/\">Cancel</a>"+
                        "</div>" +
                        "</body>" +
                        "</html>");
        out.close();
    }
}
