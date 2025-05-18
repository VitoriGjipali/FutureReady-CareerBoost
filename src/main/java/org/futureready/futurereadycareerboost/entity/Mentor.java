package org.futureready.futurereadycareerboost.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.futureready.futurereadycareerboost.entity.Appointment;

import java.util.List;
@Getter
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Mentor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String field;
    private String experience;
    private String availableTimes;
    private boolean isAvailable;

    public Mentor(String name, String field, String experience, String availableTimes, boolean isAvailable) {
        this.name = name;
        this.field = field;
        this.experience = experience;
        this.availableTimes = availableTimes;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getAvailableTimes() {
        return availableTimes;
    }

    public void setAvailableTimes(String availableTimes) {
        this.availableTimes = availableTimes;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
