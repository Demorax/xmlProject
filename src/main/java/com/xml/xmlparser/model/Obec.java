package com.xml.xmlparser.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

//@XmlRootElement

@Entity
public class Obec {


    private int id;
    private String nazev;

    public Obec() {
    }

    @Id
    public int getObecKod() {
        return id;
    }

    public void setObecKod(int obecKod) {
        this.id = obecKod;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }


}
