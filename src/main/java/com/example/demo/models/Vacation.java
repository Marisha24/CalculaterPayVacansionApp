package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Vacation {
    private Double averageSalary;
    private Integer numberOfVacationDays;
    private LocalDate startDate;

    public Vacation(Double averageSalary, Integer numberOfVacationDays) {
        this.averageSalary = averageSalary;
        this.numberOfVacationDays = numberOfVacationDays;
    }
}
