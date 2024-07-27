package com.cod.AniBirth.calendar.service;

import com.cod.AniBirth.calendar.entity.Calendar;
import com.cod.AniBirth.calendar.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public void create(String title, LocalDateTime startDate, LocalDateTime endDate) {
        Calendar calendar = Calendar.builder()
                .title(title)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        calendarRepository.save(calendar);
    }

    public List<Calendar> getAllCalendars() {
        return calendarRepository.findAll();
    }
}
