package org.example.webstore.controller;

import org.example.webstore.api.product.ProductResponse;
import org.example.webstore.entity.Product;
import org.example.webstore.repository.ProductRepository;
import org.example.webstore.service.product.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
//@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductResponse> getAllProducts() {
        //return productService.findAll();
        return productService.findAllWithCategory();
    }
}
