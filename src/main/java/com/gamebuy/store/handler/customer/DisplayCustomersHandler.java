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
import java.util.ArrayList;

public class DisplayCustomersHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("DisplayCustomersHandler Called");

		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody()));

		CustomerDAO customerDAO = new CustomerDAO();
		AddressService addressService = AddressService.getInstance();

		Address address;

		ArrayList<Customer> allCustomers = customerDAO.getAllCustomers();

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Customers</title> " +
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>Customers</h1>"+
						"<table class=\"table\">" +
						"<thead>" +
						"  <tr>" +
						"    <th>ID</th>" +
						"    <th>First name</th>" +
						"    <th>Second name</th>" +
						"    <th>Telephone number</th>" +
						"    <th>Address</th>" +
						"    <th>Actions</th>" +
						"  </tr>" +
						"</thead>" +
						"<tbody>");

		for (Customer customer : allCustomers){
			address = addressService.getCustomerAddress(customer.getId());
			out.write(
					"  <tr>"       +
							"    <td>"+ customer.getId() + "</td>" +
							"    <td>"+ customer.getFirstName() + "</td>" +
							"    <td>"+ customer.getSecondName() + "</td>" +
							"    <td>"+ customer.getTelephoneNumber() + "</td>" +
							"	 <td>" +
							"	 	<div style=\"display: flex; flex-direction: column\">" +
							"	 	<span>" + address.getHouse() + "</span>" +
							"	 	<span>" + address.getAddressLine1() + "</span>" +
							"	 	<span>" + address.getAddressLine2() + "</span>" +
							"	 	<span>" + address.getCountry() + "</span>" +
							"	 	<span>" + address.getPostcode() + "</span>" +
							"	 	</div>" +
							"	 </td>" +
							"    <td> " +
							"	 	<div style=\"display: flex; flex-direction: column\">" +
							"    		<span><a href=\"/customers/delete?id=" + customer.getId() + "\"> Delete </a></span>" +
							"    		<span><a href=\"/customers/updateForm?id=" + customer.getId() +  "\"> Update </a></span>" +
							"	 	</div>" +
							"    </td>" +
							"  </tr>"
			);
		}
		out.write(
				"</tbody>" +
						"</table>" +
						"<div>" +
						"<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/customers/addForm\">Add a new customer</a></button> " +
						"<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/\">Back to menu</a></button> " +
						"</div>" +
						"</div>" +
						"</body>" +
						"</html>");

		out.close();

	}



}
