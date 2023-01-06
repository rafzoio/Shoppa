package com.gamebuy.store.handler.product;

import com.gamebuy.store.dao.OrderItemDAO;
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

public class AdjustQuantityOfProductInBasketHandler implements HttpHandler {

    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("AdjustQuantityOfProductInBasketHandler called");
        exchange.sendResponseHeaders(200,0);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody() ));

        OrderItemService orderItemService = OrderItemService.getInstance();
        OrderItemDAO orderItemDAO = new OrderItemDAO();

        HashMap<String, String> params = requestStringToMap(exchange.getRequestURI().getQuery());

        int basketId = Integer.parseInt(params.get("basketId"));
        int productId = Integer.parseInt(params.get("productId"));

        OrderItem orderItem = orderItemService.getOrderItem(basketId, productId);

        Product product = orderItemService.getOrderItemProduct(orderItem);

        out.write(
                "<html>" +
						"<meta charset=\"utf-8\">"+
                        "<head> <title>Adjust quantity</title> "+
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">"+
                        "<h1>Adjust quantity</h1>"+
                        "<form method=\"post\" action=\"/products/adjustQuantityInBasket\">" +
                        "<div class=\"form-group\"> "+

                        "<label for=\"basketId\">Basket ID</label> " +
                        "<input value = \"" + basketId + "\" type=\"text\" class=\"form-control\" readonly=\"readonly\" name=\"basketId\" id=\"basketId\"> " +


                        "<label for=\"productId\">Product ID</label> " +
                        "<input value = \"" + product.getId() + "\" type=\"text\" class=\"form-control\" readonly=\"readonly\" name=\"productId\" id=\"productId\"> " +

                        "<label for=\"sku\">SKU</label> " +
                        "<input value = \"" + product.getSKU() + "\" type=\"text\" class=\"form-control\" name=\"sku\" id=\"sku\" disabled> " +

                        "<label for=\"description\">Description</label> " +
                        "<input value = \"" + product.getDescription() + "\" type=\"text\" class=\"form-control\" name=\"description\" id=\"description\" disabled> " +

                        "<label for=\"quantity\">Quantity</label> " +
                        "<input value = \"" + orderItem.getQuantity() + "\" type=\"number\" class=\"form-control\" name=\"quantity\" id=\"quantity\" max=\"" + product.getAvailable() +"\" required> " +

                        "</div>" +
                        "<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " +
                        "</form>" +
                        "<a href=\"/basket\">Cancel</a>"+
                        "</div>" +
                        "</body>" +
                        "</html>");

        out.close();

    }

}
