package com.gamebuy.store.handler.product;

import com.gamebuy.store.dao.ProductDAO;
import com.gamebuy.store.domain.Product;
import com.gamebuy.store.service.ProductService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;

import static com.gamebuy.store.utils.RequestStringToMap.requestInputStreamToMap;

public class DisplayProductsHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("DisplayProductsHandler called");

		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody()));

		ProductDAO productDAO = new ProductDAO();
		ProductService productService = ProductService.getInstance();

		ArrayList<Product> allProducts = productDAO.getAllProducts();


		HashMap<String, String> params = requestInputStreamToMap(exchange.getRequestBody());

		if (params.isEmpty()) {
			System.out.println(params);
			allProducts = productService.filterProductsByType(allProducts, "All");
		} else {
			String productType = params.get("productType");
			allProducts = productService.filterProductsByType(allProducts, productType);
		}

		out.write(
				"<html>" +
						"<head> <title>Products</title> " +
						"<meta charset=\"utf-8\">"+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>Products</h1>"+

						"<form method=\"post\" action=\"/products\">" +
						"<div class=\"form-group\"> "+

						"<label for=\"productType\">Filter by product type:</label> " +
						"<select class=\"form-control\" name=\"productType\" id=\"productType\">" +
						"<option value=\"All\">All</option>"
		);
		for (String type : productService.getAllProductTypes()) {
			out.write("	<option value=\""+ type + "\">" + type + "</option>");
		}
		out.write(
				"</select>" +
						"</div>" +
						"<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " +
						"</form>" +

						"<table class=\"table\">" +
						"<thead>" +
						"  <tr>" +
						"    <th>ID</th>" +
						"    <th>SKU</th>" +
						"    <th>Description</th>" +
						"    <th>Category</th>" +
						"    <th>Price</th>" +
						"	 <th>Available</th>" +
						"    <th>Actions</th>" +
						"  </tr>" +
						"</thead>" +
						"<tbody>");

		for (Product product : allProducts){
			out.write(
					"  <tr>"       +
							"    <td>"+ product.getId() + "</td>" +
							"    <td>"+ product.getSKU() + "</td>" +
							"    <td>"+ product.getDescription() + "</td>" +
							"    <td>"+ product.getCategory() + "</td>" +
							"    <td>£ "+ product.getPrice() + ".00</td>" +
							"	 <td>" + product.getAvailable() + "</td>" +
							"    <td> " +
							"	 <div style=\"display: flex; flex-direction: column\">" +
							"    <span><a href=\"/products/delete?id=" + product.getId() + "\"> Delete </a></span>" +
							"    <span><a href=\"/products/updateForm?id=" + product.getId() + "\"> Update </a></span>" +
							"    <span><a href=\"/products/addToBasketForm?id=" + product.getId() + "\"> Add to basket </a></span>" +
							"	 </div>" +
							"    </td>" +
							"  </tr>"
			);
		}
		out.write(
				"</tbody>" +
						"</table>" +
						"<p> Warning: deleting a product will also remove that product from the basket." +
						"<div>" +
						"<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/products/addForm\">Add a new product</a></button> " +
						"<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/\">Back to menu</a></button> " +
						"</div>" +
						"</div>" +

						"</body>" +
						"</html>");

		out.close();

	}



}
