package com.spimex.repository;

import com.server.entity.PaymentEntity;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<PaymentEntity, Integer> {
}
