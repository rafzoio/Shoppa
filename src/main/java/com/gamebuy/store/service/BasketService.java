package com.gamebuy.store.service;

import com.gamebuy.store.dao.OrderItemDAO;
import com.gamebuy.store.dao.ProductDAO;
import com.gamebuy.store.domain.OrderItem;

import java.util.ArrayList;

public class BasketService {

    private static BasketService instance;

    private final OrderItemDAO orderItemDAO;
    private final ProductDAO productDAO;

    private BasketService(OrderItemDAO orderItemDAO, ProductDAO productDAO) {
        this.orderItemDAO = orderItemDAO;
        this.productDAO = productDAO;
    }

    public static BasketService getInstance() {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        ProductDAO productDAO = new ProductDAO();
        if (instance == null) {
            instance = new BasketService(orderItemDAO, productDAO);
        }
        return instance;
    }

    public ArrayList<OrderItem> getOrderItemsInBasket(int basketId) {
        return orderItemDAO.getOrderItemsInBasket(basketId);
    }

    public int numberItemsInBasket(int basketId) {
        return orderItemDAO.getOrderItemsInBasket(basketId).size();
    }

    public int priceOfBasket(int basketId) {
        OrderItemService orderItemService = OrderItemService.getInstance();
        ArrayList<OrderItem> orderItemsInBasket = orderItemDAO.getOrderItemsInBasket(basketId);
        return orderItemsInBasket.stream()
                .map(orderItemService::getPriceOfOrderItem)
                .reduce(0, Integer::sum);
    }

    public void clearBasket(int basketId) {
        ArrayList<OrderItem> orderItemsInBasket = getOrderItemsInBasket(basketId);
        orderItemsInBasket.forEach(orderItem -> orderItemDAO.deleteOrderItem(basketId, orderItem.getProductId()));
    }
}
