package com.example.shoppingapp;

public class Product {
    private String name;
    private int imageResourceId;
    private int quantity;

    public Product(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
        this.quantity = 0;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}