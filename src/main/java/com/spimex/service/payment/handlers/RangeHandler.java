package com.spimex.service.payment.handlers;

import com.server.entity.PaymentEntity;
import com.spimex.service.payment.AbstractPaymentHandler;

/**
 * ONLINE платеж больше 20р и меньше 300р.
 *
 * @author Morozkin V. A. on 16.10.2022
 */
public class RangeHandler extends AbstractPaymentHandler {
    /**
     * Переменные по условиям обработчика.
     */
    private final Integer AMOUNT_MAX = 300;
    private final Integer AMOUNT_MIN = 20;
    private final Integer PERCENTAGES = 17;

    public RangeHandler(int priority) {
        super(priority);
    }

    /**
     * ONLINE, и находится в диапазоне от 20р до 300р.
     *
     * @param payment
     * @return
     */
    @Override
    public boolean permission(PaymentEntity payment) {
        return isONLINE(payment) & lessThan(payment, AMOUNT_MAX) & moreThan(payment, AMOUNT_MIN);
    }

    /**
     * Списываем платеж со счета и начисляем 17% бонусов от покупки.
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
