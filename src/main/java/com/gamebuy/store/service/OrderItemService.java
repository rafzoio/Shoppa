package com.gamebuy.store.service;

import com.gamebuy.store.dao.OrderItemDAO;
import com.gamebuy.store.dao.ProductDAO;
import com.gamebuy.store.domain.OrderItem;
import com.gamebuy.store.domain.Product;

public class OrderItemService {

    private static OrderItemService instance;
    private final OrderItemDAO orderItemDAO;
    private final ProductDAO productDAO;

    private OrderItemService(OrderItemDAO orderItemDAO, ProductDAO productDAO) {
        this.orderItemDAO = orderItemDAO;
        this.productDAO = productDAO;
    }

    public static OrderItemService getInstance() {
        OrderItemDAO orderItemDAO = new OrderItemDAO();
        ProductDAO productDAO = new ProductDAO();

        if (instance == null) {
            instance = new OrderItemService(orderItemDAO, productDAO);
        }
        return instance;
    }

    /**
     * Returns whether an OrderItem is in a basket.
     * @param basketId the basket id associated with the OrderItem
     * @param productId the product id associated with the OrderItem
     * @return boolean
     */
    public boolean isOrderItemInBasket(int basketId, int productId) {
        BasketService basketService = BasketService.getInstance();
        return basketService.getOrderItemsInBasket(basketId)
                .stream()
                .anyMatch(o -> o.getProductId() == productId);
    }

    /**
     * Gets the corresponding product of an OrderItem.
     * @param OrderItem
     * @return Product
     *
     */
    public Product getOrderItemProduct(OrderItem orderItem) {
        int productId = orderItem.getProductId();
        Product product;

        product = productDAO.getProduct(productId);

        return product;
    }

    /**
     * Gets the price of an OrderItem.
     * @param orderItem an object of class OrderItem.
     * @return int the total price of an OrderItem.
     */
    public int getPriceOfOrderItem(OrderItem orderItem) {
        int individualPrice = getOrderItemProduct(orderItem).getPrice();
        return individualPrice * orderItem.getQuantity();
    }

    public void addQuantityToOrderItem(OrderItem orderItem, int quantityToAdd) {
        int currentQuantity = orderItem.getQuantity();
        int newQuantity = currentQuantity + quantityToAdd;

        orderItemDAO.updateOrderItemQuantity(orderItem.getBasketID(), orderItem.getProductId(), newQuantity);
    }

    public void editQuantityOfOrderItem(OrderItem orderItem, int newQuantity) {
        orderItemDAO.updateOrderItemQuantity(orderItem.getBasketID(), orderItem.getProductId(), newQuantity);
    }

    public int getCurrentAvailableOrderItemQuantity(OrderItem orderItem) {
        Product product = getOrderItemProduct(orderItem);
        return product.getAvailable() -
                getOrderItem(1, product.getId()).getQuantity();
    }

    public OrderItem getOrderItem(int basketId, int productId) {
        return orderItemDAO.getOrderItemsInBasket(basketId).stream()
                .filter(orderItem -> orderItem.getProductId() == productId)
                .findFirst()
                .orElseThrow();
    }

    public void addOrderItemToBasket(OrderItem orderItem) {
        orderItemDAO.addOrderItem(orderItem.getBasketID(), orderItem.getProductId(), orderItem.getQuantity());
    }

    public void removeOrderItemFromBasket(OrderItem orderItem) {
        orderItemDAO.deleteOrderItem(orderItem.getBasketID(), orderItem.getProductId());
    }
}
