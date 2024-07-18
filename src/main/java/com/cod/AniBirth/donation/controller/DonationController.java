package com.cod.AniBirth.donation.controller;

import com.cod.AniBirth.donation.service.DonationService;
import com.cod.AniBirth.shelter.entity.Shelter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;

    @GetMapping("/dona")
    public String donationPage(Model model) {
        List<Shelter> shelters = donationService.getAllShelters();
        model.addAttribute("shelters", shelters);
        return "donation/donation";
    }

}
