package org.example.webstore.repository;

import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
import org.example.webstore.entity.OrderHead;

public class OrderHeadRepositoryImpl implements OrderHeadRepositoryCustom {

//    @PersistenceContext
//    private EntityManager entityManager;

    private final EntityManager entityManager;

    public OrderHeadRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String newOrder() {
        OrderHead orderHead = new OrderHead();
        //...
        entityManager.persist(orderHead);
        return "";
    }
}
