package com.spimex.service.payment.handlers;

import com.server.entity.PaymentEntity;
import com.server.enums.PaymentStatus;
import com.server.utils.Utils;
import com.spimex.service.payment.AbstractPaymentHandler;

/**
 * Обработчик для покупки ONLINE и меньше 20р.
 *
 * @author Morozkin V. A. on 16.10.2022
 */
public class MinimumAmountHandler extends AbstractPaymentHandler {

    /**
     * Переменные по условиям обработчика.
     */
    private final Integer AMOUNT = 20;
    private final Integer PERCENTAGES = 10;

    /**
     * ONLINE и меньше 20р.
     *
     * @param payment Платеж
     * @return true - обработать, false - едем дальше
     */
    @Override
    public boolean permission(PaymentEntity payment) {
        return isONLINE(payment) & lessThan(payment, AMOUNT);
    }

    /**
     * Списываем со чёта сумму платежа и еще 10%.
     *
     * @param payment
     */
    @Override
    public void handle(PaymentEntity payment) {
        payment.getBankAccount()
                .setOnlineCash(payment.getBankAccount().getOnlineCash()
                        - payment.getAmount()
                        - Utils.countPercent(payment.getAmount(), PERCENTAGES));

        payment.setStatus(PaymentStatus.COMPLETED);
    }
}
