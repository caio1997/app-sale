package com.home.project.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Sale implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public Double quantityTotal;

    public Double valueTotal;

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    public Client client;

    @ManyToMany
    @JoinTable(
            name = "SALE_PRODUCTS",
            uniqueConstraints = @UniqueConstraint(columnNames = {"ID_SALE", "ID_PRODUCT"}),
            joinColumns = {@JoinColumn(name = "ID_SALE")},
            inverseJoinColumns = {@JoinColumn(name = "ID_PRODUCT")})
    public List<Product> products;

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Sale(){
    }

    public Sale(Long id, Double quantityTotal, Double valueTotal, Client client, List<Product> products) {
        this.id = id;
        this.quantityTotal = quantityTotal;
        this.valueTotal = valueTotal;
        this.client = client;
        this.products = products;
    }

    public Sale(Long id, Double quantityTotal, Double valueTotal) {
        this.id = id;
        this.quantityTotal = quantityTotal;
        this.valueTotal = valueTotal;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Double getQuantityTotal() {
        return quantityTotal;
    }

    public void setQuantityTotal(Double quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

    public Double getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(Double valueTotal) {
        this.valueTotal = valueTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sale)) return false;
        Sale sale = (Sale) o;
        return Objects.equals(getId(), sale.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
