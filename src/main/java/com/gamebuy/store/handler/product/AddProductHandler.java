package com.gamebuy.store.handler.product;

import com.gamebuy.store.dao.ProductDAO;
import com.gamebuy.store.domain.Product;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import static com.gamebuy.store.utils.RequestStringToMap.requestInputStreamToMap;

public class AddProductHandler implements HttpHandler{

	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("AddProductHandler Called");
		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody()));

		HashMap<String, String> params = requestInputStreamToMap(exchange.getRequestBody());

		System.out.println(params);

		ProductDAO productDAO = new ProductDAO();

		String SKU = params.get("sku");
		String description = params.get("description");
		String category = params.get("category");
		int available = Integer.parseInt(params.get("available"));
		int price = Integer.parseInt(params.get("price"));

		Product newProduct = new Product(SKU, description, category, available, price);

		System.out.println("Product to Add" + newProduct);

		productDAO.addProduct(newProduct);

		out.write(
				"<html>" +
					"<meta charset=\"utf-8\">"+
						"<head> <title>Product Added</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>Product Added</h1>"+
						"<table class=\"table\">" +
						"<thead>" +
						"  <tr>" +
						"    <th>SKU</th>" +
						"    <th>Description</th>" +
						"    <th>Category</th>" +
						"    <th>Price</th>" +

						"  </tr>" +
						"</thead>" +
						"<tbody>" +
						"  <tr>"       +
						"    <td>"+ newProduct.getSKU() + "</td>" +
						"    <td>"+ newProduct.getDescription() + "</td>" +
						"    <td>"+ newProduct.getCategory() + "</td>" +
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
