package com.example.testproiect;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

public class Card implements Serializable {
    private String nume;
    private SexType sexType;
    private int idCard;
    private String numeMagazin;
    private Date dataEliberareCard;


    public Card(String nume, SexType sexType, int idCard, String numeMagazin, Date dataEliberareCard) {
        this.nume = nume;
        this.sexType = sexType;
        this.idCard = idCard;
        this.numeMagazin = numeMagazin;
        this.dataEliberareCard = dataEliberareCard;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getIdCard() {
        return idCard;
    }

    public void setIdCard(int idCard) {
        this.idCard = idCard;
    }

    public Date getDataEliberareCard() {
        return dataEliberareCard;
    }

    public void setDataEliberareCard(Date dataEliberareCard) {
        this.dataEliberareCard = dataEliberareCard;
    }

    public SexType getSexType() {
        return sexType;
    }

    public void setSexType(SexType sexType) {
        this.sexType = sexType;
    }

    public String getNumeMagazin() {
        return numeMagazin;
    }

    public void setNumeMagazin(String numeMagazin) {
        this.numeMagazin = numeMagazin;
    }

    @Override
    public String toString() {
        return "Cardul apartine proprietarului cu numele " + nume + '\'' +
                ", are id-ul " + idCard +
                ", a fost eliberat la data " + new DateConverter().toString(dataEliberareCard)+
                " si este pentru '" + numeMagazin + '\'' +
                '.';
    }
}
