package com.gamebuy.store.handler.product;

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

public class RemoveProductFromBasketHandler implements HttpHandler{

	public void handle(HttpExchange exchange) throws IOException {

		System.out.println("RemoveProductFromBasketHandler called");
		exchange.sendResponseHeaders(200,0);

		HashMap<String, String> params = requestStringToMap(exchange.getRequestURI().getQuery());

		int productId = Integer.parseInt(params.get("productId"));
		int basketId = Integer.parseInt(params.get("basketId"));

		OrderItem orderItem = new OrderItem(basketId, productId, 0);

		OrderItemService orderItemService = OrderItemService.getInstance();

		Product product = orderItemService.getOrderItemProduct(new OrderItem(basketId, productId, 0));

		if (orderItemService.isOrderItemInBasket(basketId, productId)) {
			orderItemService.removeOrderItemFromBasket(orderItem);
		}

		BufferedWriter out = new BufferedWriter(
				new OutputStreamWriter(exchange.getResponseBody() ));

		out.write(
				"<html>" +
						"<meta charset=\"utf-8\">"+
						"<head> <title>Item removed</title> "+
						"<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
						"</head>" +
						"<body>" +
						"<div class=\"container\">" +
						"<h1>Item removed from basket</h1>"+
						"<table class=\"table\">" +
						"<thead>" +
						"  <tr>" +
						"    <th>Product ID</th>" +
						"    <th>Product SKU</th>" +
						"    <th>Basket ID</th>" +
						"  </tr>" +
						"</thead>" +
						"<tbody>" +
				"  <tr>"       +
						"    <td>"+ productId + "</td>" +
						"    <td>"+ product.getSKU() + "</td>" +
						"    <td>"+ basketId + "</td>" +
						"  </tr>" +
				"</tbody>" +
						"</table>" +
						"<a href=\"/basket\">Back to basket</a>" +
						"</div>" +
						"</body>" +
						"</html>");

		out.close();

	}

}
