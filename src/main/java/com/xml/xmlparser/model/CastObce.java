package com.xml.xmlparser.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CastObce {
    private int id;
    private String nazev;
    private int kodObce;

    public CastObce() {
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public int getKodObce() {
        return kodObce;
    }

    public void setKodObce(int kodObce) {
        this.kodObce = kodObce;
    }


}
