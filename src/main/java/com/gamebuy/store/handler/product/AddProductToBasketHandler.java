package com.gamebuy.store.handler.product;

import com.gamebuy.store.dao.ProductDAO;
import com.gamebuy.store.domain.OrderItem;
import com.gamebuy.store.domain.Product;
import com.gamebuy.store.service.OrderItemService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import static com.gamebuy.store.utils.RequestStringToMap.requestStringToMap;

public class AddProductToBasketHandler implements HttpHandler{

	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("AddProductToBasketHandler called");
		exchange.sendResponseHeaders(200,0);

		HashMap<String, String> params = requestStringToMap(exchange.getRequestURI().getQuery());

		int productId = Integer.parseInt(params.get("id"));

		OrderItemService orderItemService = OrderItemService.getInstance();
		ProductDAO productDAO = new ProductDAO();

		OrderItem orderItem;

		int currentAvailableQuantity;

		if (orderItemService.isOrderItemInBasket(1, productId)) {
			orderItem = orderItemService.getOrderItem(1, productId);
			currentAvailableQuantity = orderItemService.getCurrentAvailableOrderItemQuantity(orderItem);
		} else {
			orderItem = new OrderItem(1, productId, 0);
			currentAvailableQuantity = productDAO.getProduct(productId).getAvailable();
		}

		Product product = orderItemService.getOrderItemProduct(orderItem);


		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody() ));

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Add to Basket</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">"+
						"<h1>Add to Basket</h1>"+
						"<form method=\"post\" action=\"/products/addToBasket\">" +
						"<div class=\"form-group\"> "+

						"<label for=\"id\">ID</label> " +
						"<input value=\"" + product.getId() + "\" type=\"text\" class=\"form-control\" readonly=\"readonly\" name=\"id\" id=\"id\"> " +

						"<label for=\"sku\">SKU</label> " +
						"<input value=\"" + product.getSKU() + "\" type=\"text\" class=\"form-control\" disabled name=\"sku\" id=\"sku\"> " +

						"<label for=\"quantity\">Quantity</label> " +
						"<input type=\"number\" class=\"form-control\" name=\"quantity\" id=\"quantity\" required max=\"" + currentAvailableQuantity + "\"> " +

						"</div>" +
						"<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " +
						"</form>" +
						"<a href=\"/products\">Cancel</a>"+
						"</div>" +
						"</body>" +
						"</html>");

		out.close();

	}

}
