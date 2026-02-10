package org.example.webstore.repository;

import org.example.webstore.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository
        extends BaseRepository<Product, Long> {

    @Query("""
        select p from Product p
        join fetch p.category
        order by p.id
    """)
    List<Product> findAllWithCategory();
}
