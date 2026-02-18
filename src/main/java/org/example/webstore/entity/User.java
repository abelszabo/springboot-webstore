package org.example.webstore.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webstore.base.BaseEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "billing_address_id")
    private Address billingAddress;

    // getters & setters
}
