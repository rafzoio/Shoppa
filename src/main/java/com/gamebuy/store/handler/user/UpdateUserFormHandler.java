package com.gamebuy.store.handler.user;

import com.gamebuy.store.dao.UserDAO;
import com.gamebuy.store.domain.Role;
import com.gamebuy.store.domain.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import static com.gamebuy.store.utils.RequestStringToMap.requestStringToMap;

public class UpdateUserFormHandler implements HttpHandler {

    /**
     * Handles form to update existing user. Posts form data to UpdateUserHandler.
     *
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("UpdateUserFormHandler called");

        exchange.sendResponseHeaders(200, 0);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody()));

        UserDAO userDAO = new UserDAO();

        Map<String, String> params = requestStringToMap(exchange.getRequestURI().getQuery());
        System.out.println(params);

        int id = Integer.parseInt(params.get("id"));

        User user;
        user = userDAO.getUserById(id);

        out.write(
                "<html>" +
                        "<meta charset=\"utf-8\">" +
                        "<head> <title>Edit User</title> " +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>Edit User</h1>" +
                        "<form method=\"post\" action=\"/users/update\">" +
                        "<div class=\"form-group\"> " +

                        "<label for=\"id\">ID</label> " +
                        "<input value=\"" + user.getId() + "\" readonly=\"readonly\" type=\"text\" class=\"form-control\" name=\"id\" id=\"id\" required> " +

                        "<label for=\"username\">Username</label> " +
                        "<input value=\"" + user.getUsername() + "\" type=\"text\" class=\"form-control\" name=\"username\" id=\"username\" required> " +

                        "<label for=\"role\">Role</label> " +
                        "<select class=\"form-control\" name=\"role\" id=\"role\">"
        );
        for (Role role : Role.values()) {
            out.write("	<option value=\"" + role + "\">" + role + "</option>");
        }
        out.write(

                "</select>" +
                        "</div>" +
                        "<button type=\"submit\" class=\"btn btn-primary\">Update</button> " +
                        "</form>" +
                        "<a href=\"/users\">Cancel</a>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

        out.close();

    }

}
