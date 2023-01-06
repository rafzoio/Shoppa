package com.gamebuy.store.handler.product;

import com.gamebuy.store.domain.OrderItem;
import com.gamebuy.store.service.OrderItemService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import static com.gamebuy.store.utils.RequestStringToMap.requestInputStreamToMap;

public class ProcessAdjustQuantityOfProductInBasketHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("ProcessAdjustQuantityOfProductInBasketHandler called");
        exchange.sendResponseHeaders(200, 0);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody()));

        OrderItemService orderItemService = OrderItemService.getInstance();

        HashMap<String, String> params = requestInputStreamToMap(exchange.getRequestBody());

        int basketId = Integer.parseInt(params.get("basketId"));
        int productId = Integer.parseInt(params.get("productId"));
        int quantity = Integer.parseInt(params.get("quantity"));

        OrderItem orderItem = orderItemService.getOrderItem(basketId, productId);

        orderItemService.editQuantityOfOrderItem(orderItem, quantity);

        out.write(
                "<html>" +
						"<meta charset=\"utf-8\">"+
                        "<head> <title>Quantity changed</title> " +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>Quantity adjusted</h1>" +
                        "<a href=\"/basket\">Return to basket</a>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

        out.close();
    }
}
