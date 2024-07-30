package com.cod.AniBirth.adopt.service;

import com.cod.AniBirth.adopt.AdoptForm;
import com.cod.AniBirth.adopt.entity.Adopt;
import com.cod.AniBirth.adopt.repository.AdoptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdoptService {
    private final AdoptRepository adoptRepository;
    public void apply(String name, String phone) {
        Adopt a = Adopt.builder()
                .name(name)
                .phone(phone)
                .build();
        adoptRepository.save(a);
    }


}
