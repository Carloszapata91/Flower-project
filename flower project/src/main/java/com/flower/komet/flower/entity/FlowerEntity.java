package com.flower.komet.flower.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "flower")
@Getter
@Setter
@NoArgsConstructor
public class FlowerEntity {
    @Id
    private String id;
    private String name;
    private Double price;
}
