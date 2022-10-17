package com.spimex.service;

import com.server.entity.BankAccountEntity;
import com.server.entity.PaymentEntity;
import com.server.enums.PaymentStatus;
import com.server.enums.PaymentType;
import com.server.exceptions.NotEnoughMoneyException;
import com.spimex.repository.BankAccountRepository;
import com.spimex.repository.PaymentRepository;
import com.spimex.service.payment.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Сервис для платежей и их обработки.
 *
 * @author Morozkin V. A. on 16.10.2022
 */
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final BankAccountRepository bankAccountRepository;
    private final PaymentRepository paymentRepository;
    private final PaymentProcessor paymentProcessor;

    /**
     * Совершаем платеж.
     * Если платеж ONLINE, то надо проверить достаточно ли средств.
     * Обработка платежа.
     * Сохранение объектов.
     *
     * @param type
     * @param amount
     */
    public void goPayment(PaymentType type, Integer amount) {
        PaymentEntity payment = new PaymentEntity(type, amount, PaymentStatus.NEW, getDefaultAccount());

        if (!paymentProcessor.checkEnoughMoney(payment)) {
            paymentRepository.save(payment);
            throw new NotEnoughMoneyException();
        }

        paymentProcessor.process(payment);
        paymentRepository.save(payment);
    }


    /**
     * Инициализация стартовых настроек.
     * Изначальная сумма в (А) = 5000 рублей.
     */
    @PostConstruct
    void initAccount() {
        bankAccountRepository.save(new BankAccountEntity(1000, 4000, 0));
    }

    /**
     * Получаем наш дефолтный банковский счет.
     *
     * @return
     */
    private BankAccountEntity getDefaultAccount() {
        return bankAccountRepository.findById(1).get();
    }
}
