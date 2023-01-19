package com.gamebuy.store.handler.customer;

import com.gamebuy.store.dao.AddressDAO;
import com.gamebuy.store.dao.CustomerDAO;
import com.gamebuy.store.domain.Customer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import static com.gamebuy.store.utils.RequestStringToMap.requestStringToMap;

public class DeleteCustomerHandler implements HttpHandler {

	/**
	 * Handles the deletion of a customer and their corresponding address from the database.
	 *
	 * @param exchange the exchange containing the request from the
	 *                 client and used to send the response
	 * @throws IOException
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("DeleteCustomerHandler called");

		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody() ));

		Map <String,String> params = requestStringToMap(exchange.getRequestURI().getQuery());
		System.out.println(params);

		int id = Integer.parseInt(params.get("id"));

		CustomerDAO customerDAO = new CustomerDAO();
		AddressDAO addressDAO = new AddressDAO();

		Customer deletedCustomer = customerDAO.getCustomer(id);

		customerDAO.deleteCustomer(id);
		addressDAO.deleteAddress(id);

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Customer Deleted</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>Customer Deleted</h1>"+
						"<table class=\"table\">" +
						"<thead>" +
						"  <tr>" +
						"    <th>ID</th>" +
						"    <th>First Name</th>" +
						"    <th>Second Name</th>" +
						"  </tr>" +
						"</thead>" +
						"<tbody>" +
				"  <tr>"       +
						"    <td>"+ deletedCustomer.getId() + "</td>" +
						"    <td>"+ deletedCustomer.getFirstName() + "</td>" +
						"    <td>"+ deletedCustomer.getSecondName() + "</td>" +
						"  </tr>" +
				"</tbody>" +
						"</table>" +
						"<a href=\"/customers\">Back to all customers</a>" +
						"</div>" +
						"</body>" +
						"</html>");

		out.close();

	}
}
