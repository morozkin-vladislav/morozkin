package com.spimex.controller.interfaces;

import org.springframework.web.bind.annotation.GetMapping;


public interface IAccountController {

    /**
     * Кол-во бонусов на счете.
     */
    @GetMapping("/bankAccountOfEMoney")
    Integer bonuses();

    /**
     * Кол-во денег на счете.
     */
    @GetMapping("/money")
    Integer money();
}
