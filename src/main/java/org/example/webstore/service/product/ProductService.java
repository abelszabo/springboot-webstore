package org.example.webstore.service.product;

import org.example.webstore.api.product.ProductResponse;
import org.example.webstore.entity.Product;
import org.example.webstore.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

//    @Transactional(readOnly = true)
//    public List<Product> findAllWithCategory() {
//        return productRepository.findAllWithCategory();
//    }

    @Transactional(readOnly = true)
    public List<ProductResponse> findAllWithCategory() {
        //return productRepository.findAll()
        return productRepository.findAllWithCategory()
                .stream()
                .map(p -> new ProductResponse(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getCategory().getName()
                ))
                .toList();
    }

    @Transactional(readOnly = true)
    public ProductResponse findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + id));

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory().getName());
    }

}
