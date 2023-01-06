package com.gamebuy.store.handler.product;

import com.gamebuy.store.dao.ProductDAO;
import com.gamebuy.store.domain.Product;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

import static com.gamebuy.store.utils.RequestStringToMap.requestStringToMap;

public class UpdateProductHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("UpdateProductHandler called");
		exchange.sendResponseHeaders(200,0);

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody() ));

		ProductDAO productDAO = new ProductDAO();

		Map <String,String> params = requestStringToMap(exchange.getRequestURI().getQuery());
		System.out.println(params);

		int id = Integer.parseInt(params.get("id"));

		Product product = null;

		product = productDAO.getProduct(id);

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Edit Product</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">"+
						"<h1>Edit Product</h1>"+
						"<form method=\"post\" action=\"/products/update\">" +
						"<div class=\"form-group\"> "+

						"<label for=\"id\">ID</label> " +
						"<input value=\"" + product.getId() + "\" readonly=\"readonly\" type=\"text\" class=\"form-control\" name=\"id\" id=\"id\" required> " +

						"<label for=\"sku\">SKU</label> " +
						"<input value=\"" + product.getSKU() + "\" type=\"text\" class=\"form-control\" name=\"sku\" id=\"sku\" required> " +

						"<label for=\"description\">Description</label> " +
						"<input value=\"" + product.getDescription() + "\" type=\"text\" class=\"form-control\" name=\"description\" id=\"description\" required> " +

						"<label for=\"category\">Category</label> " +
						"<input value=\"" + product.getCategory() + "\" type=\"text\" class=\"form-control\" name=\"category\" id=\"category\" required> " +

						"<label for=\"available\">Available</label> " +
						"<input value=\"" + product.getAvailable() + "\" type=\"text\" class=\"form-control\" name=\"available\" id=\"available\" required> " +

						"<label for=\"price\">Price</label> " +
						"<input value=\"" + product.getPrice() + "\" type=\"text\" class=\"form-control\" name=\"price\" id=\"price\" required> " +

						"</div>" +
						"<button type=\"submit\" class=\"btn btn-primary\">Edit</button> " +
						"</form>" +
						"<a href=\"/products\">Cancel</a>"+
						"</div>" +
						"</body>" +
						"</html>");

		out.close();

	}

}
