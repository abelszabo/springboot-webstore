package org.example.webstore.repository;

import org.example.webstore.entity.DeliveryType;

import java.util.Optional;

public interface DeliveryTypeRepository
        extends BaseRepository<DeliveryType, Long> {

    Optional<DeliveryType> findByCode(String code);
}
