package com.gamebuy.store.handler.customer;

import com.gamebuy.store.dao.CustomerDAO;
import com.gamebuy.store.domain.Address;
import com.gamebuy.store.domain.Customer;
import com.gamebuy.store.service.AddressService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import static com.gamebuy.store.utils.RequestStringToMap.requestInputStreamToMap;

public class UpdateCustomerHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("UpdateCustomerHandler called");
		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody()));

		CustomerDAO customerDAO = new CustomerDAO();
		AddressService addressService = AddressService.getInstance();

		HashMap<String, String> params = requestInputStreamToMap(exchange.getRequestBody());

		int id = Integer.parseInt(params.get("id"));
		String firstName = params.get("firstName");
		String secondName = params.get("secondName");
		String telephoneNumber = params.get("telephoneNumber");
		String house = params.get("house");
		String addressLine1 = params.get("addressLine1");
		String addressLine2 = params.get("addressLine2");
		String country = params.get("country");
		String postcode = params.get("postcode");

		customerDAO.updateCustomer(id, firstName, secondName, telephoneNumber);
		Customer newCustomer = customerDAO.getCustomer(id);

		Address address = new Address(id, house, addressLine1, addressLine2, country, postcode);
		addressService.updateCustomerAddress(address);

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Customer Updated</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>Customer Updated</h1>"+
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
						"	 	<span>" + address.getHouse() + "</span>" +
						"	 	<span>" + address.getAddressLine1() + "</span>" +
						"	 	<span>" + address.getAddressLine2() + "</span>" +
						"	 	<span>" + address.getCountry() + "</span>" +
						"	 	<span>" + address.getPostcode() + "</span>" +
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
