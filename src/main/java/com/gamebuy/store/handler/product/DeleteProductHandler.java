package com.gamebuy.store.handler.product;

import com.gamebuy.store.dao.OrderItemDAO;
import com.gamebuy.store.dao.ProductDAO;
import com.gamebuy.store.domain.Product;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import static com.gamebuy.store.utils.RequestStringToMap.requestStringToMap;

public class DeleteProductHandler implements HttpHandler {
	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("DeleteProductHandler called");

		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody() ));

		Map <String,String> params = requestStringToMap(exchange.getRequestURI().getQuery());
		System.out.println(params);

		int id = Integer.parseInt(params.get("id"));

		ProductDAO productDAO = new ProductDAO();
		OrderItemDAO orderItemDAO = new OrderItemDAO();

		Product deletedProduct = productDAO.getProduct(id);

		productDAO.deleteProduct(id);
		orderItemDAO.deleteOrderItem(1, id);

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Product Deleted</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>Product Deleted</h1>"+
						"<table class=\"table\">" +
						"<thead>" +
						"  <tr>" +
						"    <th>ID</th>" +
						"    <th>SKU</th>" +
						"    <th>Description</th>" +
						"    <th>Category</th>" +
						"    <th>Price</th>" +

						"  </tr>" +
						"</thead>" +
						"<tbody>" +
						"  <tr>"       +
						"    <td>"+ deletedProduct.getId() + "</td>" +
						"    <td>"+ deletedProduct.getSKU() + "</td>" +
						"    <td>"+ deletedProduct.getDescription() + "</td>" +
						"    <td>"+ deletedProduct.getCategory() + "</td>" +
						"    <td>"+ deletedProduct.getPrice() + "</td>" +
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
