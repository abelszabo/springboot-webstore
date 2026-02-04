package org.example.webstore.entity;

import org.example.webstore.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
public class Address extends BaseEntity {

    private String country;
    private String postalCode;
    private String city;
    private String street;
    private String houseNumber;
    private String floor;
    private String door;

    private String companyName;
    private String taxNumber;

    // getters & setters
}
