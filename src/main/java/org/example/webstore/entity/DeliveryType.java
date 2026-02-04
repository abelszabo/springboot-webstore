package org.example.webstore.entity;

import org.example.webstore.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "delivery_type")
public class DeliveryType extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    // getters & setters
}
