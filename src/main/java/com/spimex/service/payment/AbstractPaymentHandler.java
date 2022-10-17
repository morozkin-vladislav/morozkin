package com.spimex.service.payment;

import com.server.entity.PaymentEntity;
import com.server.enums.PaymentStatus;
import com.server.enums.PaymentType;
import com.server.utils.Utils;
import lombok.Data;

/**
 * @author Morozkin V. A. on 16.10.2022
 */
@Data
public abstract class AbstractPaymentHandler {

    public AbstractPaymentHandler(int priority) {
        this.priority = priority;
    }

    /**
     * Приоритет обработчика.
     */
    private final int priority;

    /**
     * Метод будет давать разрешение на обработку платежа.
     *
     * @return true - обработать, false - не обрабатывать
     */
    public abstract boolean permission(PaymentEntity payment);

    /**
     * Метод обработки платежа.
     */
    public abstract void handle(PaymentEntity payment);

    /**
     * Является ли платеж ONLINE
     *
     * @param payment платеж
     * @return true - да, false - нет
     */
    protected boolean isONLINE(PaymentEntity payment) {
        return payment.getType().equals(PaymentType.ONLINE);
    }

    /**
     * Является ли платеж SHOP
     *
     * @param payment платеж
     * @return true - да, false - нет
     */
    protected boolean isSHOP(PaymentEntity payment) {
        return payment.getType().equals(PaymentType.SHOP);
    }

    /**
     * Метод проверяет больше ли платеж, чем заданное число.
     *
     * @param payment платеж
     * @param number  больше какой суммы должен быть платеж
     * @return true - да, false - нет
     */
    protected boolean moreThan(PaymentEntity payment, Integer number) {
        return payment.getAmount() > number;
    }

    /**
     * Метод проверяет меньше ли платеж, чем заданное число.
     *
     * @param payment платеж
     * @param number  меньше какой суммы должен быть платеж
     * @return true - да, false - нет
     */
    protected boolean lessThan(PaymentEntity payment, Integer number) {
        return payment.getAmount() < number;
    }

    /**
     * Переводит платеж в завершенный статус.
     *
     * @param payment
     */
    protected void completed(PaymentEntity payment) {
        payment.setStatus(PaymentStatus.COMPLETED);
    }

    /**
     * Метод начисляет бонусы на счет клиента.
     *
     * @param payment платеж
     * @param percentages % от покупки, который начислим как бонус
     */
    protected void addBonus(PaymentEntity payment, Integer percentages) {
        payment.getBankAccount()
                .setBonus(payment.getBankAccount().getBonus()
                        + Utils.countPercent(payment.getAmount(), percentages));
    }

    /**
     * Метод списывает наличные деньги.
     * @param payment
     */
    protected void withdrawMoneyShop(PaymentEntity payment) {
        payment.getBankAccount()
                .setCash(payment.getBankAccount().getCash() - payment.getAmount());
    }

    /**
     * Метод списывает электронные деньги со счета.
     * @param payment
     */
    protected void withdrawMoneyOnline(PaymentEntity payment) {
        payment.getBankAccount()
                .setOnlineCash(payment.getBankAccount().getOnlineCash() - payment.getAmount());
    }
}
