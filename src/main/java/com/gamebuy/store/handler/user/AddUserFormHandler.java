package com.gamebuy.store.handler.user;

import com.gamebuy.store.domain.Role;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddUserFormHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("AddUserFormHandler called");
        exchange.sendResponseHeaders(200, 0);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody()));

        out.write(
                "<html>" +
                        "<meta charset=\"utf-8\">" +
                        "<head> <title>Add User</title> " +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>Add User</h1>" +
                        "<form method=\"post\" action=\"/users/add\">" +
                        "<div class=\"form-group\"> " +

                        "<label for=\"username\">Username</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"username\" id=\"username\" required> " +

                        "<label for=\"password\">Password</label> " +
                        "<input type=\"password\" class=\"form-control\" name=\"password\" id=\"password\" required> " +

                        "<select class=\"form-control\" name=\"role\" id=\"role\">"
        );
        for (Role role : Role.values()) {
            out.write("	<option value=\"" + role + "\">" + role + "</option>");
        }
        out.write(

                "</select>" +

                        "</div>" +
                        "<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " +
                        "</form>" +
                        "<a href=\"/users\">Cancel</a>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

        out.close();

    }

}
