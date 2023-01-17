package com.gamebuy.store;

import com.gamebuy.store.handler.MenuHandler;
import com.gamebuy.store.handler.RootHandler;
import com.gamebuy.store.handler.auth.*;
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

        server.createContext("/menu", new MenuHandler());
        server.createContext("/", new RootHandler() );
        server.createContext("/accessDenied", new AccessDeniedHandler());

        server.createContext("/auth/registerForm", new RegisterFormHandler());
        server.createContext("/auth/register", new RegisterHandler());
        server.createContext("/auth/loginForm", new LoginFormHandler());
        server.createContext("/auth/login", new LoginHandler());
        server.createContext("/auth/logout", new LogOutHandler());

        server.createContext("/products", new DisplayProductsHandler());
        server.createContext("/products/delete", new DeleteProductHandler());
        server.createContext("/products/addForm", new AddProductFormHandler());
        server.createContext("/products/add", new AddProductHandler());
        server.createContext("/products/updateForm", new UpdateProductFormHandler());
        server.createContext("/products/update", new UpdateProductHandler());

        server.createContext("/products/addToBasketForm", new AddProductToBasketFormHandler());
        server.createContext("/products/addToBasket", new AddProductToBasketHandler());
        server.createContext("/products/removeFromBasket", new RemoveProductFromBasketHandler());
        server.createContext("/products/adjustQuantityInBasketForm", new AdjustQuantityOfProductInBasketFormHandler());
        server.createContext("/products/adjustQuantityInBasket", new AdjustQuantityOfProductInBasketHandler());

        server.createContext("/customers", new DisplayCustomersHandler());
        server.createContext("/customers/addForm", new AddCustomerFormHandler());
        server.createContext("/customers/add", new AddCustomerHandler());
        server.createContext("/customers/delete", new DeleteCustomerHandler());
        server.createContext("/customers/updateForm", new UpdateCustomerFormHandler());
        server.createContext("/customers/update", new UpdateCustomerHandler());

        server.createContext("/basket", new DisplayBasketHandler());
        server.createContext("/basket/clear", new ClearBasketHandler());

        server.setExecutor(null);
        server.start();
        System.out.println("The server is listening on port " + PORT);

        //ConsoleApp.consoleApp();
    }
}