package com.example.testproiect.oferte;

import java.io.Serializable;

public class Oferta implements Serializable {
    private String denumire;
    private String text;
    private int valabilitate; // zile

    public Oferta(String denumire, String text, int valabilitate) {
        this.denumire = denumire;
        this.text = text;
        this.valabilitate = valabilitate;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getValabilitate() {
        return valabilitate;
    }

    public void setValabilitate(int valabilitate) {
        this.valabilitate = valabilitate;
    }

    @Override
    public String toString() {
        return   "Oferta"+denumire +
                " " + text + '\'' +
                " este valabila " + valabilitate + " zile ";
    }
}
