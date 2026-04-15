package com.spart.code.code1.Repository;

import com.spart.code.code1.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}