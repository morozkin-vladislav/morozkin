package com.spimex.service.payment.handlers;

import com.server.entity.PaymentEntity;
import com.spimex.service.payment.AbstractPaymentHandler;

/**
 * Обработчик для платежей наличными.
 *
 * @author Morozkin V. A. on 16.10.2022
 */
public class ShopHandler extends AbstractPaymentHandler {
    /**
     * Переменные по условиям обработчика.
     */
    private final Integer PERCENTAGES = 10;

    /**
     * Обработать, если тип платежа SHOP.
     *
     * @param payment Платеж
     * @return true - обработать, false - едем дальше
     */
    @Override
    public boolean permission(PaymentEntity payment) {
        return isSHOP(payment);
    }

    /**
     * Платеж наличными, значит снимаем деньги со счёта наличных
     * и начисляем 10% бонусов от покупки.
     *
     * @param payment платеж
     */
    @Override
    public void handle(PaymentEntity payment) {
        withdrawMoneyShop(payment);
        addBonus(payment, PERCENTAGES);
        completed(payment);
    }

}
