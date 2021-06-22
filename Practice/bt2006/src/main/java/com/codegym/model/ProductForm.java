package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private Long id;
    private String name;
    private String descriptions;
    private Category category_id;
    private MultipartFile image;

    public ProductForm() {
    }

    public ProductForm(Long id, String name, String descriptions, Category category_id, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.descriptions = descriptions;
        this.category_id = category_id;
        this.image = image;
    }

    public ProductForm(String name, String descriptions, Category category_id, MultipartFile image) {
        this.name = name;
        this.descriptions = descriptions;
        this.category_id = category_id;
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

    public Category getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Category category_id) {
        this.category_id = category_id;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
