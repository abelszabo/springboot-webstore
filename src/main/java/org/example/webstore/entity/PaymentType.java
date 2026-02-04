package org.example.webstore.entity;

import org.example.webstore.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "payment_type")
public class PaymentType extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    // getters & setters
}
