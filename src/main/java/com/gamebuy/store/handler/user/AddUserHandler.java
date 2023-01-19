package com.gamebuy.store.handler.user;

import com.gamebuy.store.dao.UserDAO;
import com.gamebuy.store.domain.Role;
import com.gamebuy.store.domain.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import static com.gamebuy.store.utils.RequestStringToMap.requestInputStreamToMap;

public class AddUserHandler implements HttpHandler {

    /**
     * Handles adding of a new user to the database.
     *
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("ProcessAddProductHandler Called");
        exchange.sendResponseHeaders(200, 0);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody()));

        HashMap<String, String> params = requestInputStreamToMap(exchange.getRequestBody());

        System.out.println(params);

        UserDAO userDAO = new UserDAO();

        String username = params.get("username");
        String password = params.get("password");
        Role role = Role.valueOf(params.get("role"));

        User user = new User(username, password, role);

        userDAO.addUser(user);

        out.write(
                "<html>" +
                        "<meta charset=\"utf-8\">" +
                        "<head> <title>User Added</title> " +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>User Added</h1>" +
                        "<table class=\"table\">" +
                        "<thead>" +
                        "  <tr>" +
                        "    <th>Username</th>" +
                        "    <th>Role</th>" +

                        "  </tr>" +
                        "</thead>" +
                        "<tbody>" +
                        "  <tr>" +
                        "    <td>" + user.getUsername() + "</td>" +
                        "    <td>" + user.getRole().name() + "</td>" +
                        "  </tr>" +
                        "</tbody>" +
                        "</table>" +
                        "<a href=\"/users\">Back to all users</a>" +
                        "</div>" +
                        "</body>" +
                        "</html>");
        out.close();
    }

}
