package com.cod.AniBirth.animal;

import lombok.Data;

@Data
public class AnimalSearchDTO {
    private String keyword;
    private Long classification;
    private Long gender;
    private Long weight;
    private Long age;
}
