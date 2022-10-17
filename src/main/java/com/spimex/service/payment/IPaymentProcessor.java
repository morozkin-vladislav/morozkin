package com.spimex.service.payment;

import com.server.entity.PaymentEntity;
import com.server.exceptions.NotEnoughMoneyException;

/**
 * Интерфейс для платежного процессора.
 */
public interface IPaymentProcessor {

    void initHandlers();

    void process(PaymentEntity payment);

    boolean checkEnoughMoney(PaymentEntity payment) throws NotEnoughMoneyException;
}
