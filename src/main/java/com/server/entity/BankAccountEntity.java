package com.server.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Класс счета в Банке.
 *
 * @author Morozkin V. A. on 14.10.2022
 */
@Entity(name = "bank_account")
@Data
@NoArgsConstructor
public class BankAccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * Будем считать, что это сумма есть на руках у клиента.
     */
    private Integer cash;

    /**
     * Сумма, которая есть у клиента на счете Банка.
     */
    private Integer onlineCash;

    /**
     * Бонусы, которые получает клиент за покупки.
     */
    private Integer bonus;


    public BankAccountEntity(Integer cash, Integer onlineCash, Integer bonus) {
        this.cash = cash;
        this.onlineCash = onlineCash;
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BankAccountEntity that = (BankAccountEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
