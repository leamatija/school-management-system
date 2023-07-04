package com.internship.spring.project.schoolmanagementsystem.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DateUtils {

    public static List<LocalDate> generateDates(LocalDate startDate, LocalDate endDate, List<Integer> onDayOfWeek,
                                                Optional<List<LocalDate>> holidays){

        // Predicate 1: Is a given date is a holiday
        Predicate<LocalDate> isHoliday = date -> holidays.isPresent()
                && holidays.get().contains(date);

        // Predicate 2: Is a given date is a weekday
        Predicate<LocalDate> isWeekend = date -> date.getDayOfWeek() == DayOfWeek.SATURDAY
                || date.getDayOfWeek() == DayOfWeek.SUNDAY;

        var generatedDates = onDayOfWeek.stream().map(d->{
            Predicate<LocalDate> isTargetWeekDay = date -> date.getDayOfWeek().equals(DayOfWeek.of(d));
           return startDate.datesUntil(endDate)
                    .filter(isWeekend.or(isHoliday).negate())
                    .filter(isTargetWeekDay)
                    .collect(Collectors.toList());
        } ) .flatMap(d-> d.stream()).collect(Collectors.toList());
        return generatedDates;
    }
}
