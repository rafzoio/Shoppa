package com.gamebuy.store.handler.product;

import com.gamebuy.store.dao.ProductDAO;
import com.gamebuy.store.domain.Product;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.HashMap;

import static com.gamebuy.store.utils.RequestStringToMap.requestInputStreamToMap;

public class UpdateProductHandler implements HttpHandler {

	/**
	 * Handles the updating of an existing product.
	 *
	 * @param exchange the exchange containing the request from the
	 *                 client and used to send the response
	 * @throws IOException
	 */
	@Override
	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("UpdateProductHandler called");
		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody()));

		HashMap<String, String> params = requestInputStreamToMap(exchange.getRequestBody());

		ProductDAO productDAO = new ProductDAO();

		int id = Integer.parseInt(params.get("id"));
		String sku = params.get("sku");
		String description = params.get("description");
		String category = params.get("category");
		int available = Integer.parseInt(params.get("available"));
		int price = Integer.parseInt(params.get("price"));

		productDAO.updateProduct(id, sku, description, category, available, price);
		Product newProduct = productDAO.getProduct(id);

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Product Updated</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>Product Updated</h1>"+
						"<table class=\"table\">" +
						"<thead>" +
						"  <tr>" +
						"    <th>ID</th>" +
						"    <th>SKU</th>" +
						"    <th>Description</th>" +
						"    <th>Category</th>" +
						"    <th>Available</th>" +
						"    <th>Price</th>" +

						"  </tr>" +
						"</thead>" +
						"<tbody>" +
						"  <tr>"       +
						"    <td>"+ newProduct.getId() + "</td>" +
						"    <td>"+ newProduct.getSKU() + "</td>" +
						"    <td>"+ newProduct.getDescription() + "</td>" +
						"    <td>"+ newProduct.getCategory() + "</td>" +
						"    <td>"+ newProduct.getAvailable() + "</td>" +
						"    <td>"+ newProduct.getPrice() + "</td>" +
						"  </tr>" +
						"</tbody>" +
						"</table>" +
						"<a href=\"/products\">Back to all products</a>" +
						"</div>" +
						"</body>" +
						"</html>");

		out.close();
	}
}
