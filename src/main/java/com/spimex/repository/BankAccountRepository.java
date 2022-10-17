package com.spimex.repository;

import com.server.entity.BankAccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccountEntity, Integer> {
}
