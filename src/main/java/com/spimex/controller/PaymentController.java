package com.spimex.controller;

import com.server.enums.PaymentType;
import com.spimex.controller.interfaces.IPaymentController;
import com.spimex.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Morozkin V. A. on 14.10.2022
 */
@RestController
@RequiredArgsConstructor
public class PaymentController implements IPaymentController {

    private final PaymentService paymentService;

    @Override
    public void payment(String type, Integer amount) {
        paymentService.goPayment(PaymentType.valueOf(type.toUpperCase()), amount);
    }
}
