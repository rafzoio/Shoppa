package com.gamebuy.store.handler.product;

import com.gamebuy.store.domain.OrderItem;
import com.gamebuy.store.service.OrderItemService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.util.HashMap;

import static com.gamebuy.store.utils.RequestStringToMap.requestInputStreamToMap;

public class ProcessAddProductToBasketHandler implements HttpHandler{

	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("ProcessAddProductToBasketHandler called");
		exchange.sendResponseHeaders(200,0);

		OrderItemService orderItemService = OrderItemService.getInstance();

		URI requestURI = exchange.getRequestURI();
		HashMap<String, String> params = requestInputStreamToMap(exchange.getRequestBody());
		int productId = Integer.parseInt(params.get("id"));
		int basketId = 1;
		int quantityToAdd = Integer.parseInt(params.get("quantity"));

		boolean orderItemExists = orderItemService.isOrderItemInBasket(basketId, productId);

		OrderItem orderItem = new OrderItem(basketId, productId, 0);

		if (orderItemExists) {
			orderItemService.addQuantityToOrderItem(orderItem, quantityToAdd);
		} else {
			orderItem.setQuantity(quantityToAdd);
			orderItemService.addOrderItemToBasket(orderItem);
		}

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody() ));

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Added to Basket</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">"+
						"<h1>Successfully added to basket</h1>"+
						"<a href=\"/products\">Back to products</a>" +
						"</br>"+
						"<a href=\"/basket\">Continue to basket</a>"+
						"</div>" +
						"</body>" +
						"</html>");

		out.close();

	}

}
