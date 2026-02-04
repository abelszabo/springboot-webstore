package org.example.webstore.repository;

import org.example.webstore.entity.InvoiceHead;

import java.util.Optional;

public interface InvoiceHeadRepository
        extends BaseRepository<InvoiceHead, Long> {

    Optional<InvoiceHead> findByInvoiceNumber(String invoiceNumber);

    boolean existsByOrderId(Long orderId);
}
