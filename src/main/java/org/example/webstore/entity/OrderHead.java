package org.example.webstore.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.webstore.base.BaseEntity;
import jakarta.persistence.*;
import org.example.webstore.entity.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "order_head",
        indexes = @Index(name = "ux_order_number", columnList = "orderNumber", unique = true)
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderHead extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String orderNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Setter(AccessLevel.NONE)
    private OrderStatus status;

    private BigDecimal totalGross;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private PaymentType paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    private DeliveryType deliveryType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    private Address billingAddress;

    @OneToMany(
            mappedBy = "orderHead",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderItem> items = new ArrayList<>();

    /* =====================
       DOMAIN COMMANDS
       ===================== */

    public void confirmCheckout() {
        if (status != OrderStatus.DRAFT) {
            throw new IllegalStateException(
                    "Checkout can only be confirmed from DRAFT"
            );
        }
        this.status = OrderStatus.CHECKOUT_CONFIRMED;
    }

    public void startPayment() {
        if (status != OrderStatus.CHECKOUT_CONFIRMED) {
            throw new IllegalStateException(
                    "Payment can only start after checkout confirmation"
            );
        }
        this.status = OrderStatus.PAYMENT_IN_PROGRESS;
    }

    public void markPaid() {
        if (status != OrderStatus.PAYMENT_IN_PROGRESS) {
            throw new IllegalStateException(
                    "Order can only be marked PAID from PAYMENT_IN_PROGRESS"
            );
        }
        this.status = OrderStatus.PAID;
    }

    public void cancel() {
        if (status == OrderStatus.PAID) {
            throw new IllegalStateException(
                    "Paid order cannot be cancelled"
            );
        }
        this.status = OrderStatus.CANCELLED;
    }
}
