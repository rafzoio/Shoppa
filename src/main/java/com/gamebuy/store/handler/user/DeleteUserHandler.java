package com.gamebuy.store.handler.user;

import com.gamebuy.store.dao.UserDAO;
import com.gamebuy.store.domain.User;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import static com.gamebuy.store.utils.RequestStringToMap.requestStringToMap;

public class DeleteUserHandler implements HttpHandler {
	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("DeleteUserHandler called");

		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody() ));

		Map <String,String> params = requestStringToMap(exchange.getRequestURI().getQuery());
		System.out.println(params);

		int id = Integer.parseInt(params.get("id"));

		UserDAO userDAO = new UserDAO();

		User deletedUser = userDAO.getUserById(id);

		userDAO.deleteUser(id);

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>User Deleted</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>User Deleted</h1>"+
						"<table class=\"table\">" +
						"<thead>" +
						"  <tr>" +
						"    <th>ID</th>" +
						"    <th>username</th>" +
						"  </tr>" +
						"</thead>" +
						"<tbody>" +
						"  <tr>"       +
						"    <td>"+ deletedUser.getId() + "</td>" +
						"    <td>"+ deletedUser.getUsername() + "</td>" +
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
