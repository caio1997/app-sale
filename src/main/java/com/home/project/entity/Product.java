package com.home.project.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    private String description;

    @NotNull
    private Double quantity;

    @NotNull
    private Double price;

    public Product(){
    }

    public Product(Long id, String name, String description, Double quantity, Double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id;}

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getQuantity() { return quantity; }

    public void setQuantity(Double quantity) { this.quantity = quantity; }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
