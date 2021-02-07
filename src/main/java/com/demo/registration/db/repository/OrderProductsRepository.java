package com.demo.registration.db.repository;

import com.demo.registration.db.entity.OrderProducts;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderProductsRepository extends CrudRepository<OrderProducts, Integer> {

    List<OrderProducts> findByPurchase(Integer order);
}
