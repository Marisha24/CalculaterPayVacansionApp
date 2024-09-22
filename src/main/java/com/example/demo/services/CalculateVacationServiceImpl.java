package com.example.demo.services;

import com.example.demo.models.Vacation;
import org.springframework.stereotype.Service;


@Service
public class CalculateVacationServiceImpl implements CalculateVacationService{
    /** Среднее количество дней в месяце без учета федеральных праздников */
    private static final double AVERAGE_NUMBER_DAYS_IN_MOUNT = 29.3;
    /**
     * Метод для расчёта отпускных без учёта праздников и выходных
     * Формула: ЗП / 29,3 × кол-во дней отпуска
     * @return возвращает сумму отпускных, которая придет сотруднику
     */
    @Override
    public double vacationPayCalculator(Vacation vacation) {
        double vacationPay = 0;
        if(averageSalaryIsValid(vacation.getAverageSalary()) && vacationDaysIsValid(vacation.getNumberOfVacationDays())){
          vacationPay = vacation.getAverageSalary()/AVERAGE_NUMBER_DAYS_IN_MOUNT * vacation.getNumberOfVacationDays();
        }
        return vacationPay;
    }

    /**
     * Метод для расчёта отпускных с учётом праздников и выходных
     *  Формула: ЗП / 29,3 × кол-во дней отпуска
     * @return возвращает сумму отпускных, которая придет сотруднику
     */
    @Override
    public double vacationPayCalculatorByDay(Double numberOfDay,Vacation vacation) {
        double vacationPay = 0;
        if(averageSalaryIsValid(vacation.getAverageSalary()) && vacationDaysIsValid(vacation.getNumberOfVacationDays())&&startDateIsValid(numberOfDay)) {
            vacationPay = vacation.getAverageSalary() /AVERAGE_NUMBER_DAYS_IN_MOUNT * numberOfDay;
        }
        return vacationPay;

    }


    /**
     * Методы валидации
     */
    private boolean averageSalaryIsValid(Double averageSalary) {
        return averageSalary != null && averageSalary > 0;
    }

    private boolean vacationDaysIsValid(Integer numberOfVacationDays){
        return numberOfVacationDays != null && numberOfVacationDays > 0;
    }
    private boolean startDateIsValid(Double startDate) {
        return startDate != null;
    }
}
