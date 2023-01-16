package com.gamebuy.store.handler.customer;

import com.gamebuy.store.dao.AddressDAO;
import com.gamebuy.store.dao.CustomerDAO;
import com.gamebuy.store.domain.Address;
import com.gamebuy.store.domain.Customer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import static com.gamebuy.store.utils.RequestStringToMap.requestInputStreamToMap;

public class AddCustomerHandler implements HttpHandler{

	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("ProcessAddCustomerHandler Called");
		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody()));

		HashMap<String, String> params = requestInputStreamToMap(exchange.getRequestBody());

		CustomerDAO customerDAO = new CustomerDAO();
		AddressDAO addressDAO = new AddressDAO();

		String firstName = params.get("firstName");
		String secondName = params.get("secondName");
		String house = params.get("house");
		String addressLine1 = params.get("addressLine1");
		String addressLine2 = params.get("addressLine2");
		String country = params.get("country");
		String postcode = params.get("postcode");
		String telephoneNumber = params.get("telephoneNumber");

		Customer newCustomer = new Customer(firstName, secondName, telephoneNumber);

		int customerId = customerDAO.addCustomer(newCustomer);

		Address newAddress = new Address(customerId, house, addressLine1, addressLine2, country, postcode);

		System.out.println(newAddress);
		addressDAO.addAddress(newAddress);

		System.out.println("Product to Add" + newCustomer);

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Customer Added</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>Customer Added</h1>"+
						"<table class=\"table\">" +
						"<thead>" +
						"  <tr>" +
						"    <th>First Name</th>" +
						"    <th>Second Name</th>" +
						"    <th>Telephone Number</th>" +
						"    <th>Address</th>" +
						"  </tr>" +
						"</thead>" +
						"<tbody>" +
						"  <tr>"       +
						"    <td>"+ newCustomer.getFirstName() + "</td>" +
						"    <td>"+ newCustomer.getSecondName() + "</td>" +
						"    <td>"+ newCustomer.getTelephoneNumber() + "</td>" +
						"	 <td>" +
						"	 	<div style=\"display: flex; flex-direction: column\">" +
						"	 	<span>" + newAddress.getHouse() + "</span>" +
						"	 	<span>" + newAddress.getAddressLine1() + "</span>" +
						"	 	<span>" + newAddress.getAddressLine2() + "</span>" +
						"	 	<span>" + newAddress.getCountry() + "</span>" +
						"	 	<span>" + newAddress.getPostcode() + "</span>" +
						"	 	</div>" +
						"	 </td>" +
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
