package com.spimex.controller;

import com.spimex.controller.interfaces.IAccountController;
import com.spimex.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Morozkin V. A. on 16.10.2022
 */
@RestController
@RequiredArgsConstructor
public class AccountController implements IAccountController {

    private final AccountService accountService;

    @Override
    public Integer bonuses() {
        return accountService.getBonuses();
    }

    @Override
    public Integer money() {
        return accountService.getMoney();
    }
}
