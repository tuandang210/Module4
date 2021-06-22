package com.codegym.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String intro;
    private String producer;

    public Product() {
    }

    public Product(int id, String name, double price, String intro, String producer) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.intro = intro;
        this.producer = producer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
