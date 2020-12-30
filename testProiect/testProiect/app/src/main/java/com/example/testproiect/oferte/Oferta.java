package com.example.testproiect.oferte;

import java.io.Serializable;

public class Oferta implements Serializable {
    private String denumire;
    private String text;
    private int image;

    public Oferta(String denumire, String text, int img) {
        this.denumire = denumire;
        this.text = text;
        this.image=img;
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
    public int getImage() {
        return image;
    }
    public void setImage(int image) {
        this.image = image;
    }
    @Override
    public String toString() {
        return  denumire + '\'' +
                ". " + text + '\'' ;
    }
}
