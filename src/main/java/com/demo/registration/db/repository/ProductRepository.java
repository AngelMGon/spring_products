package com.demo.registration.db.repository;

import com.demo.registration.db.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
