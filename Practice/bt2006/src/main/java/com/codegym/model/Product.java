package com.codegym.model;

import javax.persistence.*;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String descriptions;
    @ManyToOne
    @JoinColumn
    private Category category;
    private String image;

    public Product() {
    }

    public Product(String name, String descriptions, Category category, String image) {
        this.name = name;
        this.descriptions = descriptions;
        this.category = category;
        this.image = image;
    }

    public Product(Long id, String name, String descriptions, Category category, String image) {
        this.id = id;
        this.name = name;
        this.descriptions = descriptions;
        this.category = category;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
