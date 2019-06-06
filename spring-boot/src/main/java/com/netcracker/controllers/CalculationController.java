package com.netcracker.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculation")
public class CalculationController {

    @RequestMapping(value = "/add/{a}/{b}", method = RequestMethod.GET)
    public double add(@PathVariable("a") double a, @PathVariable("b") double b) {
        double result = a + b;
        System.out.println("Result of add: " + result);
        return result;

    }

    @RequestMapping(value = "/sub/{a}/{b}", method = RequestMethod.GET)
    public double subtraction(@PathVariable("a") double a, @PathVariable("b") double b) {
        double result = a - b;
        System.out.println("Result of subtraction: " + result);
        return result;
    }

    @RequestMapping(value = "/mult/{a}/{b}", method = RequestMethod.GET)
    public double multiply(@PathVariable("a") double a, @PathVariable("b") double b) {
        double result = a * b;
        System.out.println("Result of multiply: " + result);
        return result;
    }

    @RequestMapping(value = "/div/{a}/{b}", method = RequestMethod.GET)
    public double division(@PathVariable("a") double a, @PathVariable("b") double b) {
        double result = a / b;
        System.out.println("Result of division: " + result);
        return result;
    }



}

