package com.home.project.service;

import com.home.project.entity.Product;
import com.home.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAll(){ return productRepository.findAll(); }

    public Product save(Product product) { return productRepository.save(product); }

    public Optional<Product> findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            product.get();
        }else {
            throw new RuntimeException("Product not found for id: " + id);
        }
        return product;
    }

    public void delete(Long id) { productRepository.deleteById(id); }

    /*
    public Product update(Product product, Long id)  {

        Product productUpdate = productRepository.getOne(id);
        productUpdate.setName(product.getName());
        productUpdate.setDescription(product.getDescription());
        productUpdate.setPrice(product.getPrice());
        productUpdate.setQuantity(product.getQuantity());
        productRepository.save(productUpdate);
        return productUpdate;
    }
    */

    public Product getOne(Long id) { return productRepository.getOne(id);}
}
