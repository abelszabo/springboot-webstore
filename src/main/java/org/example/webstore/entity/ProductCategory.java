package org.example.webstore.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.webstore.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "product_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductCategory extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_category_id")
    private ProductCategory parentCategory;

    // getters & setters
}
