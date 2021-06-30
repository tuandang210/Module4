package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class ProductForm {
    private Long id;
    @NotEmpty(message = "You must type a name for this")
    @Size(min = 1, max = 50, message = "Minimum one word and maximum is fifty words")
    private String name;
    @Size(min = 1, max = 50, message = "Minimum one word and maximum is fifty words")
    private String descriptions;
    private Category category;
    private MultipartFile image;

    public ProductForm() {
    }

    public ProductForm(Long id, String name, String descriptions, Category category, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.descriptions = descriptions;
        this.category = category;
        this.image = image;
    }

    public ProductForm(String name, String descriptions, Category category, MultipartFile image) {
        this.name = name;
        this.descriptions = descriptions;
        this.category = category;
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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
