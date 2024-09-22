package com.example.demo.controllers;


import com.example.demo.models.Vacation;

import com.example.demo.services.CalculatePayServiceImpl;
import com.example.demo.services.CalculateVacationServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@Validated
public class CalculateController {

    final CalculateVacationServiceImpl vacantionService;
   final CalculatePayServiceImpl calculateHoliday;
    public CalculateController(CalculateVacationServiceImpl calculateVacantionService, CalculatePayServiceImpl calculateHoliday) {
        this.vacantionService = calculateVacantionService;
        this.calculateHoliday = calculateHoliday;
    }

    @GetMapping("/calculacte")
    public double calculate(
            @RequestParam (value = "yearSalary", required = true)  Double yearSalary,
            @RequestParam (value="vacationDays", required = true) Integer vacationDays,
            @RequestParam(value = "startDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate
    ){


        if (startDate != null) {

            Vacation vacationPay = new Vacation(yearSalary, vacationDays,startDate);
            double countPayDay = calculateHoliday.calculatePaidDays(vacationPay);
            return vacantionService.vacationPayCalculatorByDay(countPayDay, vacationPay);
        } else {
            Vacation vacationPay = new Vacation(yearSalary, vacationDays);
            return vacantionService.vacationPayCalculator(vacationPay);
        }

    }


}
