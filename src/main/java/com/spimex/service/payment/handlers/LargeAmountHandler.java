package com.spimex.service.payment.handlers;

import com.server.entity.PaymentEntity;
import com.spimex.service.payment.AbstractPaymentHandler;

/**
 * ONLINE платеж больше 300р
 *
 * @author Morozkin V. A. on 16.10.2022
 */
public class LargeAmountHandler extends AbstractPaymentHandler {

    /**
     * Переменные по условиям обработчика.
     */
    private final Integer AMOUNT = 300;
    private final Integer PERCENTAGES = 30;

    /**
     * ONLINE и больше 300;
     *
     * @param payment Платеж
     * @return true - обработать, false - едем дальше
     */
    @Override
    public boolean permission(PaymentEntity payment) {
        return isONLINE(payment) & moreThan(payment, AMOUNT);
    }

    /**
     * Списываем платеж со счета и начисляем 30% бонусов от покупки.
     *
     * @param payment
     */
    @Override
    public void handle(PaymentEntity payment) {
        withdrawMoneyOnline(payment);
        addBonus(payment, PERCENTAGES);
        completed(payment);
    }
}
