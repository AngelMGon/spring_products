package com.demo.registration.controller;

import com.demo.registration.db.entity.OrderProducts;
import com.demo.registration.db.entity.Purchase;
import com.demo.registration.db.repository.OrderProductsRepository;
import com.demo.registration.db.repository.OrderRepository;
import com.demo.registration.vo.OrderWebRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value= "/orders")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderProductsRepository orderProductsRepository;

    @CrossOrigin(origins = "*")
    @PostMapping
    public @ResponseBody
    Purchase createOrder (@RequestBody OrderWebRequest request) {
        Purchase n = new Purchase();
        n.setUser(request.getUser());
        n.setTotal(request.getTotal());
        n.setCreationDate(LocalDateTime.now());
        n = orderRepository.save(n);

        for(Integer productId : request.getProducts()){
            orderProductsRepository.save(new OrderProducts(n.getId(),productId));
        }

        return n;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "{id}")
    public @ResponseBody
    OrderWebRequest getOrderById(@PathVariable Integer id) {
        List<Integer> products = new ArrayList<>();
        Purchase purchase = orderRepository.findById(id).get();
        List<OrderProducts> orderProducts = orderProductsRepository.findByPurchase(id);
        OrderWebRequest response = new OrderWebRequest();
        response.setId(purchase.getId());
        response.setUser(purchase.getUser());
        response.setTotal(purchase.getTotal());
        response.setCreationDate(purchase.getCreationDate());
        for(OrderProducts orderProduct : orderProducts){
            products.add(orderProduct.getProduct());
        }
        response.setProducts(products);

        return response;
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/user/{id}")
    public @ResponseBody
    List<OrderWebRequest> getOrdersByUser(@PathVariable Integer id) {
        List<OrderWebRequest> orders = new ArrayList<>();
        List<Purchase> purchaseList = orderRepository.findByUserOrderByCreationDateDesc(id);

        for(Purchase purchase : purchaseList) {
            List<Integer> products = new ArrayList<>();
            List<OrderProducts> orderProducts = orderProductsRepository.findByPurchase(purchase.getId());
            OrderWebRequest response = new OrderWebRequest();
            response.setId(purchase.getId());
            response.setUser(purchase.getUser());
            response.setTotal(purchase.getTotal());
            response.setCreationDate(purchase.getCreationDate());
            for (OrderProducts orderProduct : orderProducts) {
                products.add(orderProduct.getProduct());
            }
            response.setProducts(products);
            orders.add(response);
        }

        return orders;
    }

}
