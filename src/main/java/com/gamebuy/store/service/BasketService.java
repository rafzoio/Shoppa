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

    /**
     * Returns an ArrayList of all ordeItems in the basket.
     *
     * @param basketId
     * @return ArrayList of all orderItems contained in a basket.
     */
    public ArrayList<OrderItem> getOrderItemsInBasket(int basketId) {
        return orderItemDAO.getOrderItemsInBasket(basketId);
    }

    /**
     * Returns the number of orderItems in a basket.
     *
     * @param basketId
     * @return int number of orderItem contained in basket.
     */
    public int numberOfOrderItemsInBasket(int basketId) {
        return orderItemDAO.getOrderItemsInBasket(basketId).size();
    }

    /**
     * Returns the current total value of a basket.
     *
     * @param basketId
     * @return int total price of all products in a basket.
     */
    public int priceOfBasket(int basketId) {
        OrderItemService orderItemService = OrderItemService.getInstance();
        ArrayList<OrderItem> orderItemsInBasket = orderItemDAO.getOrderItemsInBasket(basketId);
        return orderItemsInBasket.stream()
                .map(orderItemService::getPriceOfOrderItem)
                .reduce(0, Integer::sum);
    }

    /**
     * Removes all orderItems from a specified basket.
     *
     * @param basketId
     */
    public void clearBasket(int basketId) {
        ArrayList<OrderItem> orderItemsInBasket = getOrderItemsInBasket(basketId);
        orderItemsInBasket.forEach(orderItem -> orderItemDAO.deleteOrderItem(basketId, orderItem.getProductId()));
    }
}
