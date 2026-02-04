package org.example.webstore.service.outbox;

import org.example.webstore.entity.OutboxEvent;
import org.example.webstore.repository.OutboxEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OutboxPublisherService {

    private final OutboxEventRepository outboxEventRepository;

    public OutboxPublisherService(OutboxEventRepository outboxEventRepository) {
        this.outboxEventRepository = outboxEventRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void publishOrderPaid(UUID orderUuid) {

//        OutboxEvent event = OutboxEvent.orderPaid(orderUuid);
//        outboxEventRepository.save(event);
    }
}
