package com.ds.mobileapi.controller;

import com.ds.mobileapi.service.TwillioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class TwillioController {

    @Autowired
    TwillioService twillioService;

    @Value("${app.twillio.fromPhoneNo}")
    private String from;

    @Value("${app.twillio.toPhoneNo}")
    private String to;


    @GetMapping("/sendSms")
    public String sendSms(@RequestParam(name = "amount", required = true) String totalAmount,
            @RequestParam(name = "pin") String pinNO) {

        System.out.println("called");
        System.out.println("PaymentService : " + totalAmount + " ==> " + pinNO);
        
        String body = "\nYour Total Amount : Rs. " + totalAmount+" /="
        + "\nYour PIN code: " + pinNO;
        twillioService.sendSms(to, from, body);
        return body;

    }

}
