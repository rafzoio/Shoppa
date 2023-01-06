package com.gamebuy.store.domain;

public class OrderItem {
    private int basketID;
    private int productId;
    private int quantity;

    public OrderItem(int basketID, int productId, int quantity) {
        this.basketID = basketID;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBasketID() {
        return basketID;
    }

    public void setBasketID(int basketID) {
        this.basketID = basketID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "basketID=" + basketID +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
