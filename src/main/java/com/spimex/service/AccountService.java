package com.spimex.service;

import com.spimex.repository.BankAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Сервис для банковских счетов.
 *
 * @author Morozkin V. A. on 16.10.2022
 */
@Service
@RequiredArgsConstructor
public class AccountService {
    private final BankAccountRepository bankAccountRepository;

    /**
     * Получить кол-во бонусов на счету.
     */
    public Integer getBonuses() {
        return bankAccountRepository.findById(1).get().getBonus();
    }

    /**
     * Получить кол-во денег на счету.
     */
    public Integer getMoney() {
        return bankAccountRepository.findById(1).get().getOnlineCash();
    }
}
