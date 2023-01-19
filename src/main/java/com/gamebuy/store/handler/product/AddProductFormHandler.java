package com.gamebuy.store.handler.product;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class AddProductFormHandler implements HttpHandler {

    /**
     * Handles form to add a new product. Form data is posted to AddProductHandler.
     *
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        System.out.println("AddProductFormHandler called");
        exchange.sendResponseHeaders(200, 0);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(exchange.getResponseBody()));

        out.write(
                "<html>" +
                        "<meta charset=\"utf-8\">" +
                        "<head> <title>Add Product</title> " +
                        "<link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css\" integrity=\"sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2\" crossorigin=\"anonymous\">" +
                        "</head>" +
                        "<body>" +
                        "<div class=\"container\">" +
                        "<h1>Add Product</h1>" +
                        "<form method=\"post\" action=\"/products/add\">" +
                        "<div class=\"form-group\"> " +

                        "<label for=\"sku\">SKU</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"sku\" id=\"sku\" required> " +

                        "<label for=\"description\">Description</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"description\" id=\"description\" required> " +

                        "<label for=\"category\">Category</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"category\" id=\"category\" required> " +

                        "<label for=\"available\">Available</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"available\" id=\"available\" required> " +

                        "<label for=\"price\">Price</label> " +
                        "<input type=\"text\" class=\"form-control\" name=\"price\" id=\"price\" required> " +

                        "</div>" +
                        "<button type=\"submit\" class=\"btn btn-primary\">Submit</button> " +
                        "</form>" +
                        "<a href=\"/products\">Cancel</a>" +
                        "</div>" +
                        "</body>" +
                        "</html>");

        out.close();

    }

}
