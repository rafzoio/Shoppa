package com.gamebuy.store.service;

import com.gamebuy.store.dao.OrderItemDAO;
import com.gamebuy.store.domain.OrderItem;

import java.util.ArrayList;

public class BasketService {

    private static BasketService instance;

    private final OrderItemDAO orderItemDAO;

    private BasketService(OrderItemDAO orderItemDAO) {
        this.orderItemDAO = orderItemDAO;
    }

    public static BasketService getInstance() {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        if (instance == null) {
            instance = new BasketService(orderItemDAO);
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
