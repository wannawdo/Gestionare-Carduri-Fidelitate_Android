package com.example.testproiect.magazine.partenere;

import java.io.Serializable;

public class Magazin implements Serializable {
    private String numeMagazin;
    private int imgMagazin;

    public Magazin(String numeMagazin, int imgMagazin) {
        this.numeMagazin = numeMagazin;
        this.imgMagazin = imgMagazin;
    }

    public String getNumeMagazin() {
        return numeMagazin;
    }

    public void setNumeMagazin(String numeMagazin) {
        this.numeMagazin = numeMagazin;
    }

    public int getImgMagazin() {
        return imgMagazin;
    }

    public void setImgMagazin(int imgMagazin) {
        this.imgMagazin = imgMagazin;
    }

    @Override
    public String toString() {
        return numeMagazin + '\'' ;
    }
}
