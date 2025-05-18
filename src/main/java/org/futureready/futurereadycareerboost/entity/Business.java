package org.futureready.futurereadycareerboost.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Business {
    @Id
    @GeneratedValue
    private Long id;
}
