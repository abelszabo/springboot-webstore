package org.example.webstore.repository;

import org.example.webstore.entity.PaymentType;

import java.util.Optional;

public interface PaymentTypeRepository
        extends BaseRepository<PaymentType, Long> {

    Optional<PaymentType> findByCode(String code);
}
