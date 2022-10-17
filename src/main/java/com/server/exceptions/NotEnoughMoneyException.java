package com.server.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Исключение на случай если не хватает денег на счете у клиента.
 *
 * @author Morozkin V. A. on 16.10.2022
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException() {
        super("Уважаемый клиент! На Вашем счете не достаточно средств!");
    }
}
