package com.cod.AniBirth.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/introduce")
public class IntroduceController {
    @GetMapping("/anibirth")
    public String intro() {

        return "introduce/anibirth";
    }
}
