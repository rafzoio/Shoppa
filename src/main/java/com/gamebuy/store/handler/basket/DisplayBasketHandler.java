package com.gamebuy.store.handler.basket;

import com.gamebuy.store.domain.OrderItem;
import com.gamebuy.store.domain.Product;
import com.gamebuy.store.domain.Role;
import com.gamebuy.store.service.BasketService;
import com.gamebuy.store.service.LoginService;
import com.gamebuy.store.service.OrderItemService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DisplayBasketHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("DisplayBasketHandler called");

        LoginService loginService = LoginService.getInstance();
        BasketService basketService = BasketService.getInstance();
        OrderItemService orderItemService = OrderItemService.getInstance();

        if (loginService.checkRoleOfCurrentUser(Role.CUSTOMER)) {
            exchange.sendResponseHeaders(200,0);
        } else {
            exchange.getResponseHeaders().add("Location", "http://localhost:8090/accessDenied");
            exchange.sendResponseHeaders(307,0);
        }

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody()));

        ArrayList<OrderItem> itemsInBasket = basketService.getOrderItemsInBasket(1);

        out.write(
                "<html>" +
                        "<head> " +
                        "<title>Basket</title> " +
                        "<meta charset=\"utf-8\">" +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>Basket (" + basketService.numberItemsInBasket(1) + ")</h1>" +
                        "<table class=\"table\">" +
                        "<thead>" +
                        "  <tr>" +
                        "    <th>Product</th>" +
                        "    <th>Description</th>" +
                        "    <th>Category</th>" +
                        "    <th>Product Price</th>" +
                        "    <th>Quantity</th>" +
                        "    <th>Price for Quantity</th>" +
                        "    <th>Actions</th>" +
                        "  </tr>" +
                        "</thead>" +
                        "<tbody>");

        for (OrderItem orderItem : itemsInBasket) {
            Product product = orderItemService.getOrderItemProduct(orderItem);
            out.write(
                    "  <tr>" +
                            "    <td>" + product.getSKU() + "</td>" +
                            "    <td>" + product.getDescription() + "</td>" +
                            "    <td>" + product.getCategory() + "</td>" +
                            "    <td>£ " + product.getPrice() + ".00</td>" +
                            "    <td>" + orderItem.getQuantity() + "</td>" +
                            "    <td>£ " + orderItemService.getPriceOfOrderItem(orderItem) + ".00</td>" +
                            "    <td> " +
                            "	 	<div style=\"display: flex; flex-direction: column\">" +
                            "    		<span><a href=\"/products/removeFromBasket?productId=" + orderItem.getProductId() + "&basketId=1\">Remove from basket </a></span>" +
                            "    		<span><a href=\"/products/adjustQuantityInBasketForm?productId=" + orderItem.getProductId() + "&basketId=1\">Adjust quantity </a></span>" +
                            "	 	</div>" +
                            "    </td>" +
                            "  </tr>");
        }
        out.write(
                "</tbody>" +
                        "</table>" +
                        "<p>Total price: £ " + basketService.priceOfBasket(1) + ".00</p>" +
                        "<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/basket/clear\">Clear Basket</a></button> " +
                        "<button type=\"button\" class=\"btn bg-transparent btn-outline-primary\"><a href=\"/menu\">Back to menu</a></button> " +
                        "</div>" +

                        "</body>" +
                        "</html>");
        out.close();
    }
}
