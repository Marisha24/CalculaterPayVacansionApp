package com.example.demo.services;

import com.example.demo.models.Vacation;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CalculatePayServiceImpl implements CalculatePayDayService{

    /** Текущий год */
    public final static int CURRENT_YEAR = LocalDate.now().getYear();

    /**
     * Метод для расчёта количества оплачиваемых дней в отпуске с учётом праздников и выходных
     * @return возвращает количество оплачиваемых дней отпуска
     */
     @Override
    public int calculatePaidDays(Vacation vacation) {

        Predicate<LocalDate> holidays = getHolidays()::contains;
        List<LocalDate> listPaidVacationDate = Stream.iterate(vacation.getStartDate(), nextVacationDate -> nextVacationDate.plusDays(1)).limit(vacation.getNumberOfVacationDays())
                .filter(vacationDate -> !(holidays.test(vacationDate)))
                .filter(vacationDate -> !(vacationDate.getDayOfWeek() == DayOfWeek.SATURDAY || vacationDate.getDayOfWeek() == DayOfWeek.SUNDAY))
                .collect(Collectors.toList());
        return listPaidVacationDate.size();
    }

    /**
     * Метод для хранения праздничных дней в формате LocalDate
     * @return возвращает список List, состоящий из праздничных дней
     */
    public static List<LocalDate> getHolidays() {
        List<LocalDate> holidays = Stream.of(
                LocalDate.of(CURRENT_YEAR, 1, 1),
                LocalDate.of(CURRENT_YEAR, 1, 2),
                LocalDate.of(CURRENT_YEAR, 1, 3),
                LocalDate.of(CURRENT_YEAR, 1, 4),
                LocalDate.of(CURRENT_YEAR, 1, 5),
                LocalDate.of(CURRENT_YEAR, 1, 6),
                LocalDate.of(CURRENT_YEAR, 1, 7),
                LocalDate.of(CURRENT_YEAR, 1, 8),
                LocalDate.of(CURRENT_YEAR, 2, 23),
                LocalDate.of(CURRENT_YEAR, 3, 8),
                LocalDate.of(CURRENT_YEAR, 5, 1),
                LocalDate.of(CURRENT_YEAR, 5, 9),
                LocalDate.of(CURRENT_YEAR, 6, 12),
                LocalDate.of(CURRENT_YEAR, 11, 4)
        ).collect(Collectors.toList());

        return Collections.unmodifiableList(holidays);
    }


}
