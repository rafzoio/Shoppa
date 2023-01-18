package com.gamebuy.store.handler.user;

import com.gamebuy.store.dao.UserDAO;
import com.gamebuy.store.domain.Role;
import com.gamebuy.store.domain.User;
import com.gamebuy.store.service.LoginService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DisplayUsersHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("DisplayUsersHandler Called");

		LoginService loginService = LoginService.getInstance();
		UserDAO userDAO = new UserDAO();

		if (loginService.checkRoleOfCurrentUser(Role.ADMIN)) {
			exchange.sendResponseHeaders(200,0);
		} else {
			exchange.getResponseHeaders().add("Location", "http://localhost:8090/accessDenied");
			exchange.sendResponseHeaders(307,0);
		}

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody()));

		ArrayList<User> allUsers = userDAO.getAllUsers();

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Users</title> " +
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>Users</h1>"+
						"<table class=\"table\">" +
						"<thead>" +
						"  <tr>" +
						"    <th>User ID</th>" +
						"    <th>Username</th>" +
						"    <th>Role</th>" +
						"    <th>Actions</th>" +
						"  </tr>" +
						"</thead>" +
						"<tbody>");

		for (User user : allUsers){
			out.write(
					"  <tr>"       +
							"    <td>"+ user.getId() + "</td>" +
							"    <td>"+ user.getUsername() + "</td>" +
							"    <td>"+ user.getRole() + "</td>" +
							"    <td> " +
							"	 	<div style=\"display: flex; flex-direction: column\">" +
							"    		<span><a href=\"/users/delete?id=" + user.getId() + "\"> Delete </a></span>" +
							"    		<span><a href=\"/users/updateForm?id=" + user.getId() +  "\"> Update </a></span>" +
							"	 	</div>" +
							"    </td>" +
							"  </tr>"
			);
		}
		out.write(
				"</tbody>" +
						"</table>" +
						"<div>" +
						"<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/users/addForm\">Add a new customer</a></button> " +
						"<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/menu\">Back to menu</a></button> " +
						"</div>" +
						"</div>" +
						"</body>" +
						"</html>");

		out.close();

	}
}
