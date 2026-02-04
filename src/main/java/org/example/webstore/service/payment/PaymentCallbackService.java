package org.example.webstore.service.payment;

import org.example.webstore.service.order.OrderService;
import org.example.webstore.service.outbox.OutboxPublisherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class PaymentCallbackService {

    private final OrderService orderService;
    private final OutboxPublisherService outboxPublisherService;

    public PaymentCallbackService(OrderService orderService,
                                  OutboxPublisherService outboxPublisherService) {
        this.orderService = orderService;
        this.outboxPublisherService = outboxPublisherService;
    }

    @Transactional
    public void handlePaymentSuccess(UUID orderUuid) {

        orderService.finalizeOrder(orderUuid);

        // new TX
        outboxPublisherService.publishOrderPaid(orderUuid);
    }
}
