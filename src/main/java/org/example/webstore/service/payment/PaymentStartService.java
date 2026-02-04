package org.example.webstore.service.payment;

import org.example.webstore.entity.OrderHead;
import org.example.webstore.entity.enums.OrderStatus;
import org.example.webstore.repository.OrderHeadRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentStartService {

    private final OrderHeadRepository orderHeadRepository;

    public PaymentStartService(OrderHeadRepository orderHeadRepository) {
        this.orderHeadRepository = orderHeadRepository;
    }

    public String startPayment(UUID orderUuid) {

        OrderHead order =
                orderHeadRepository.findByUuid(orderUuid)
                        .orElseThrow(() ->
                                new IllegalStateException("Order not found"));

        if (order.getStatus() != OrderStatus.PAYMENT_IN_PROGRESS) {
            throw new IllegalStateException(
                    "Payment not allowed for this order");
        }

        // mock payment gateway URL
        return "https://payment-gateway/pay?order=" + order.getUuid();
    }
}
