package com.cod.AniBirth.article.service;

import com.cod.AniBirth.article.entity.Qa;
import com.cod.AniBirth.article.repository.QaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QaService {

    private final QaRepository qaRepository;

    public List<Qa> getAllQas() {
        return qaRepository.findAll();
    }

    public Qa getQaById(Long id) {
        return qaRepository.findById(id).orElse(null);
    }

    public Qa saveQa(Qa qa) {
        return qaRepository.save(qa);
    }

    public void deleteQa(Long id) {
        qaRepository.deleteById(id);
    }
}
