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
import java.util.Map;

import static com.gamebuy.store.utils.RequestStringToMap.requestStringToMap;

public class UpdateCustomerFormHandler implements HttpHandler {

	/**
	 * Handles the form to update an existing customer. Form data is posted to UpdateCustomerHandler.
	 * @param exchange the exchange containing the request from the
	 *                 client and used to send the response
	 * @throws IOException
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("UpdateCustomerFormHandler called");
		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody() ));

		CustomerDAO customerDAO = new CustomerDAO();
		AddressService addressService = AddressService.getInstance();

		Map <String,String> params = requestStringToMap(exchange.getRequestURI().getQuery());
		System.out.println(params);

		int id = Integer.parseInt(params.get("id"));

		Customer customer;

		customer = customerDAO.getCustomer(id);

		Address address = addressService.getCustomerAddress(id);

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> " +
						"<title>Edit Customer</title> "+
						"<meta charset=\"utf-8\">"+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">"+
						"<h1>Edit Customer</h1>"+
						"<form method=\"post\" action=\"/customers/update\">" +
						"<div class=\"form-group\"> "+

						"<label for=\"id\">ID</label> " +
						"<input value=\"" + customer.getId() + "\" readonly=\"readonly\" type=\"text\" class=\"form-control\" name=\"id\" id=\"id\" required> " +

						"<label for=\"firstName\">First name</label> " +
						"<input value=\"" + customer.getFirstName() + "\" type=\"text\" class=\"form-control\" name=\"firstName\" id=\"firstName\" required> " +

						"<label for=\"secondName\">Second name</label> " +
						"<input value=\"" + customer.getSecondName() + "\" type=\"text\" class=\"form-control\" name=\"secondName\" id=\"secondName\" required> " +

						"<label for=\"telephoneNumber\">Telephone number</label> " +
						"<input value=\"" + customer.getTelephoneNumber() + "\" type=\"text\" class=\"form-control\" name=\"telephoneNumber\" id=\"telephoneNumber\" required> " +

						"<label for=\"house\">House</label> " +
						"<input value=\"" + address.getHouse() + "\" type=\"text\" class=\"form-control\" name=\"house\" id=\"house\" required> " +

						"<label for=\"addressLine1\">Address line 1</label> " +
						"<input value=\"" + address.getAddressLine1() + "\" type=\"text\" class=\"form-control\" name=\"addressLine1\" id=\"addressLine1\" required> " +

						"<label for=\"addressLine2\">Address line 2</label> " +
						"<input value=\"" + address.getAddressLine2() + "\" type=\"text\" class=\"form-control\" name=\"addressLine2\" id=\"addressLine2\" required> " +

						"<label for=\"country\">Country</label> " +
						"<input value=\"" + address.getCountry() + "\" type=\"text\" class=\"form-control\" name=\"country\" id=\"country\" required> " +

						"<label for=\"postcode\">Postcode</label> " +
						"<input value=\"" + address.getPostcode() + "\" type=\"text\" class=\"form-control\" name=\"postcode\" id=\"postcode\" required> " +

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
