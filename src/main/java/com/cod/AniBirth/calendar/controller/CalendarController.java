package com.cod.AniBirth.calendar.controller;

import com.cod.AniBirth.calendar.entity.Calendar;
import com.cod.AniBirth.calendar.service.CalendarService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;

    @GetMapping("/events")
    public List<Calendar> getEvents() {
        return calendarService.getAllCalendars();
    }
}