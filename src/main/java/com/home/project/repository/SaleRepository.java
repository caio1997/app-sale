package com.home.project.repository;

import com.home.project.entity.Product;
import com.home.project.entity.Sale;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
}
