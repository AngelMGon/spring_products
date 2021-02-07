package com.demo.registration.db.repository;

import com.demo.registration.db.entity.Purchase;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Purchase, Integer> {

    List<Purchase> findByUserOrderByCreationDateDesc(Integer user);
}
