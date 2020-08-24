package com.home.project.service;

import com.home.project.entity.Product;
import com.home.project.entity.Sale;
import com.home.project.repository.ProductRepository;
import com.home.project.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    public List<Sale> findAll(){ return saleRepository.findAll(); }

    public Sale save(Sale sale) { return saleRepository.save(sale); }

    public Sale findById(Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        if (sale.isPresent()) {
            sale.get();
        } else {
            throw new RuntimeException("Product not found for id: " + id);
        }
        return sale.get();
    }

    public void delete(Long id) { saleRepository.deleteById(id); }

    public Sale getOne(Long id) { return saleRepository.getOne(id);}
}
