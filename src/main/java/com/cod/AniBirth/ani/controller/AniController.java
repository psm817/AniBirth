package com.cod.AniBirth.ani.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AniController {
    @GetMapping("/ani")
    public String main() {
        return "ani/ani";
    }
}