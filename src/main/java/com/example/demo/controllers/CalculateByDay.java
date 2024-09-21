package com.example.demo.controllers;


import com.example.demo.models.Vacation;
import com.example.demo.services.CalculateVacationServiceImpl;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class CalculateByDay {
    final CalculateVacationServiceImpl vacantionService;
    public CalculateByDay(CalculateVacationServiceImpl calculateVacantionService) {
        this.vacantionService = calculateVacantionService;
    }

    @GetMapping("/calculacteByDay")
    public double calculate(
            @RequestParam Double yearSalary,
            @RequestParam Integer vacationDays,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate)
            {
        Vacation vacationPay = new Vacation(yearSalary, vacationDays,startDate);
        double countPayDay = vacantionService.CalculatorByDay(vacationPay);
        return vacantionService.vacationPayCalculatorByDay(countPayDay, vacationPay);
    }
}
