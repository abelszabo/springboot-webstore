package org.example.webstore.repository;

import org.example.webstore.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryRepository
        extends BaseRepository<ProductCategory, Long> {

    List<ProductCategory> findByParentCategoryIsNull();
}
