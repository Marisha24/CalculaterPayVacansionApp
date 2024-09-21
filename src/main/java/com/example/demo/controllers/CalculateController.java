package com.example.demo.controllers;


import com.example.demo.models.Vacation;
import com.example.demo.services.CalculateVacationServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class CalculateController {

    final CalculateVacationServiceImpl vacantionService;

    public CalculateController(CalculateVacationServiceImpl calculateVacantionService) {
        this.vacantionService = calculateVacantionService;
    }

    @GetMapping("/calculacte")
    public double calculate(
            @RequestParam Double yearSalary,
            @RequestParam Integer vacationDays
    ){
        Vacation vacationPay = new Vacation(yearSalary, vacationDays);
        return vacantionService.vacationPayCalculator(vacationPay);
    }


}
