package com.example.demo.services;


import com.example.demo.models.Vacation;

public interface CalculateVacationService {
    double vacationPayCalculator(Vacation vacation);
    double vacationPayCalculatorByDay(Double numberOfDay, Vacation vacation);




}
