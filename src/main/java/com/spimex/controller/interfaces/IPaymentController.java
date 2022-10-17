package com.spimex.controller.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/payment")
public interface IPaymentController {
    @PostMapping("/{target}/{amount}")
    void payment(@PathVariable("target") String type, @PathVariable("amount") Integer amount);
}
