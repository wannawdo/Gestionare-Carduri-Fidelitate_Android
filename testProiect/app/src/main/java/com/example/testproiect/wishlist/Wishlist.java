package com.example.testproiect.wishlist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "wishlist")
public class Wishlist implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "denumire")
    private String denumire;
    @ColumnInfo(name = "magazinProvenineta")
    private String magazinProvenineta;
    @ColumnInfo(name = "pret")
    private Double pret;

    public Wishlist(long id, String denumire, String magazinProvenineta, Double pret) {
        this.id = id;
        this.denumire = denumire;
        this.magazinProvenineta = magazinProvenineta;
        this.pret = pret;
    }
    @Ignore
    public Wishlist(String denumire, String magazinProvenineta, Double pret) {
        this.denumire = denumire;
        this.magazinProvenineta = magazinProvenineta;
        this.pret = pret;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getMagazinProvenineta() {
        return magazinProvenineta;
    }

    public void setMagazinProvenineta(String magazinProvenineta) {
        this.magazinProvenineta = magazinProvenineta;
    }

    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Denumire produs:'" + denumire +
                "Magazin provenineta='" + magazinProvenineta  +
                "Pret=" + pret ;
    }
}
