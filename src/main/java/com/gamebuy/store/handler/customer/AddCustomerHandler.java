package com.gamebuy.store.handler.customer;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddCustomerHandler implements HttpHandler{

	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("AddCustomerHandler called");
		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody() ));

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Add Customer</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">"+
						"<h1>Add Customer</h1>"+
						"<form method=\"post\" action=\"/customers/add\">" +
						"<div class=\"form-group\"> "+

						"<label for=\"firstName\">First name</label> " +
						"<input type=\"text\" class=\"form-control\" name=\"firstName\" id=\"firstName\" required> " +

						"<label for=\"secondName\">Second name</label> " +
						"<input type=\"text\" class=\"form-control\" name=\"secondName\" id=\"secondName\" required> " +

						"<label for=\"telephoneNumber\">Telephone number</label> " +
						"<input type=\"text\" class=\"form-control\" name=\"telephoneNumber\" id=\"telephoneNumber\" required> " +

						"<label for=\"house\">House</label> " +
						"<input type=\"text\" class=\"form-control\" name=\"house\" id=\"house\" required> " +

						"<label for=\"addressLine1\">Address line 1</label> " +
						"<input type=\"text\" class=\"form-control\" name=\"addressLine1\" id=\"addressLine1\" required> " +

						"<label for=\"addressLine2\">Address line 2</label> " +
						"<input type=\"text\" class=\"form-control\" name=\"addressLine2\" id=\"addressLine2\" required> " +

						"<label for=\"country\">Country</label> " +
						"<input type=\"text\" class=\"form-control\" name=\"country\" id=\"country\" required> " +

						"<label for=\"postcode\">Postcode</label> " +
						"<input type=\"text\" class=\"form-control\" name=\"postcode\" id=\"postcode\" required> " +

						"</div>" +
						"<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " +
						"</form>" +
						"<a href=\"/customers\">Cancel</a>"+
						"</div>" +
						"</body>" +
						"</html>");

		out.close();

	}

}
