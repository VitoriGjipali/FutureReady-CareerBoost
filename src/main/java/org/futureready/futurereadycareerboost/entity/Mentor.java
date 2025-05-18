package org.futureready.futurereadycareerboost.model;

import jakarta.persistence.*;

@Entity
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emri;
    private String fusha;
    private String eksperienca;
    private String oraretELira;
    private boolean isAvailable;

    public Mentor() {
    }

    public Mentor(String emri, String fusha, String eksperienca, String oraretELira, boolean isAvailable) {
        this.emri = emri;
        this.fusha = fusha;
        this.eksperienca = eksperienca;
        this.oraretELira = oraretELira;
        this.isAvailable = isAvailable;
    }

    public Long getId() {
        return id;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getFusha() {
        return fusha;
    }

    public void setFusha(String fusha) {
        this.fusha = fusha;
    }

    public String getEksperienca() {
        return eksperienca;
    }

    public void setEksperienca(String eksperienca) {
        this.eksperienca = eksperienca;
    }

    public String getOraretELira() {
        return oraretELira;
    }

    public void setOraretELira(String oraretELira) {
        this.oraretELira = oraretELira;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
