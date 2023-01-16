package com.gamebuy.store.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class RootHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody()));

		out.write(
				"<html>" +
						"<head> <title>Home</title> "+
						"<meta charset=\"utf-8\">"+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>GameBuy</h1>" +
						"<p>Login or register below:</p>" +
						"<br></br>" +
						"<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/auth/loginForm\">Login</a></button> " +
						"<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/auth/registerForm\">Register</a></button> " +

						"</div>" +
						"</body>" +
						"</html>"
		);
		out.close();

	}



}
