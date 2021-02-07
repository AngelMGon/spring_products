package com.demo.registration.controller;

import com.demo.registration.db.entity.Product;
import com.demo.registration.db.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequestMapping(value= "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @CrossOrigin(origins = "*")
    @PostMapping
    public @ResponseBody
    String addNewProduct (@RequestParam String name
            , @RequestParam String description, @RequestParam Long price) {

        Product n = new Product();
        n.setName(name);
        n.setDescription(description);
        n.setPrice(price);
        productRepository.save(n);
        return "Saved";
    }

    @CrossOrigin(origins = "*")
    @GetMapping
    public @ResponseBody Iterable<Product> getAllProducts() {
        // This returns a JSON or XML with the users
        return productRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "{id}")
    public @ResponseBody
    Optional<Product> getProductById(@PathVariable Integer id) {
        // This returns a JSON or XML with the users
        return productRepository.findById(id);
    }

}
