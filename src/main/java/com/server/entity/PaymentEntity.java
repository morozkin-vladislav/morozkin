package com.server.entity;

import com.server.enums.PaymentStatus;
import com.server.enums.PaymentType;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author Morozkin V. A. on 14.10.2022
 */
@Entity(name = "payment")
@Data
@NoArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Дата платежа.
     */
    private Date date;

    /**
     * Тип операции (онлайн/наличные).
     */
    private PaymentType type;

    /**
     * Сумма платежа (покупки).
     */
    private Integer amount;

    /**
     * Статус платежа
     */
    private PaymentStatus status;

    /**
     * Комментарий всегда пригодится на случай ошибки.
     */
    private String comment;

    /**
     * Счет, с которого спишем платеж и начислим бонусы
     * или спишем комиссию.
     */
    @ManyToOne
    private BankAccountEntity bankAccount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentEntity payment = (PaymentEntity) o;
        return id != null && Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public PaymentEntity(PaymentType type, Integer amount, PaymentStatus status, BankAccountEntity bankAccount) {
        this.type = type;
        this.amount = amount;
        this.status = status;
        this.bankAccount = bankAccount;
        this.date = new Date();
    }
}
