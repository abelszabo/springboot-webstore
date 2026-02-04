package org.example.webstore.entity;

import org.example.webstore.base.BaseEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "invoice_head",
        indexes = @Index(name = "ux_invoice_number", columnList = "invoiceNumber", unique = true)
)
public class InvoiceHead extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String invoiceNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private OrderHead order;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address billingAddress;

    private BigDecimal totalNet;
    private BigDecimal totalVat;
    private BigDecimal totalGross;

    @OneToMany(
            mappedBy = "invoiceHead",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<InvoiceItem> items = new ArrayList<>();

    // getters & setters
}
