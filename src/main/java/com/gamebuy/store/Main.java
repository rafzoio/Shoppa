package com.gamebuy.store;

import com.gamebuy.store.handler.RootHandler;
import com.gamebuy.store.handler.basket.ClearBasketHandler;
import com.gamebuy.store.handler.basket.DisplayBasketHandler;
import com.gamebuy.store.handler.customer.*;
import com.gamebuy.store.handler.product.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Main {

    static final private int PORT = 8090;

    public static void main(String[] args) throws IOException {

        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);

        server.createContext("/", new RootHandler() );

        server.createContext("/products", new DisplayProductsHandler());
        server.createContext("/products/delete", new DeleteProductHandler());
        server.createContext("/products/addForm", new AddProductHandler());
        server.createContext("/products/add", new ProcessAddProductHandler());
        server.createContext("/products/updateForm", new UpdateProductHandler());
        server.createContext("/products/update", new ProcessUpdateProductHandler());

        server.createContext("/products/addToBasketForm", new AddProductToBasketHandler());
        server.createContext("/products/addToBasket", new ProcessAddProductToBasketHandler());
        server.createContext("/products/removeFromBasket", new RemoveProductFromBasketHandler());
        server.createContext("/products/adjustQuantityInBasketForm", new AdjustQuantityOfProductInBasketHandler());
        server.createContext("/products/adjustQuantityInBasket", new ProcessAdjustQuantityOfProductInBasketHandler());

        server.createContext("/customers", new DisplayCustomersHandler());
        server.createContext("/customers/addForm", new AddCustomerHandler());
        server.createContext("/customers/add", new ProcessAddCustomerHandler());
        server.createContext("/customers/delete", new DeleteCustomerHandler());
        server.createContext("/customers/updateForm", new UpdateCustomerHandler());
        server.createContext("/customers/update", new ProcessUpdateCustomerHandler());

        server.createContext("/basket", new DisplayBasketHandler());
        server.createContext("/basket/clear", new ClearBasketHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("The server is listening on port " + PORT);

        //ConsoleApp.consoleApp();
    }
}