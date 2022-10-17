package com.spimex.service.payment;

import com.server.entity.PaymentEntity;
import com.server.enums.PaymentStatus;
import com.server.enums.PaymentType;
import com.spimex.service.payment.handlers.LargeAmountHandler;
import com.spimex.service.payment.handlers.MinimumAmountHandler;
import com.spimex.service.payment.handlers.RangeHandler;
import com.spimex.service.payment.handlers.ShopHandler;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

/**
 * Класс инициализирует обработчики и запускает обработку платежей.
 *
 * @author Morozkin V. A. on 16.10.2022
 */
@Component
@NoArgsConstructor
public class PaymentProcessor implements IPaymentProcessor {

    /**
     * Список обработчиков платежа.
     */
    private ArrayList<AbstractPaymentHandler> handlers;

    /**
     * Метод инициализирует обработчики с определенным приоритетом,
     * что и будет определять порядок обработки платежа.
     * <p>
     * Такой подход позволяет быстро создать и добавить
     * (или убрать) обработчик любого приоритета, если это потребуется.
     */
    @Override
    @PostConstruct //метод вызывается сразу после внедрения зависимости
    public void initHandlers() {
        int n = 0; //приоритет обработчика
        handlers = new ArrayList<>();

        //примерно по тестовым данным определил приоритет для каждого обработчика
        handlers.add(new LargeAmountHandler(n++));
        handlers.add(new RangeHandler(n++));
        handlers.add(new MinimumAmountHandler(n++));
        handlers.add(new ShopHandler(n));
    }

    /**
     * Метод запускает обработку платежа.
     * Проверяем обработчиками на условия выполнения обработки.
     * Обработали и завершаем цикл.
     *
     * @param payment платеж
     */
    @Override
    public void process(PaymentEntity payment) {
        for (AbstractPaymentHandler handler : handlers) {
            if (handler.permission(payment)) {
                handler.handle(payment);
                break;
            }
        }
        //Можно переделать. Если потребуется, чтобы платеж обрабатывался несколькими обработчиками сразу:
        //handlers.stream().filter(h -> h.permission(payment)).forEach(h -> h.handle(payment));
    }

    /**
     * Перед обработкой обязательно нужно проверить,
     * что есть деньги на счёте у клиента.
     *
     * @param payment
     */
    public boolean checkEnoughMoney(PaymentEntity payment) {
        if (payment.getType().equals(PaymentType.ONLINE)
                & (payment.getBankAccount().getOnlineCash() - payment.getAmount()) < 0) {
            payment.setStatus(PaymentStatus.ERROR);
            payment.setComment("Было не достаточно средств на счете.");
            return false;
        } else if (payment.getType().equals(PaymentType.SHOP)
                & (payment.getBankAccount().getCash() - payment.getAmount()) < 0) {
            payment.setStatus(PaymentStatus.ERROR);
            payment.setComment("Было не достаточно наличных средств.");
            return false;
        }
        return true;
    }

}
