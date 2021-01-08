package com.example.testproiect.coduri.promotionale;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "coduripromotionale")
public class CodPromotional implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;
    @ColumnInfo(name = "codPromotional")
    private String codPromotional;
    @ColumnInfo(name = "magazin")
    private String magazin;
    @ColumnInfo(name = "reducere")
    private Double reducere;

    public CodPromotional(long id, String codPromotional, String magazin, Double reducere) {
        this.id = id;
        this.codPromotional = codPromotional;
        this.magazin = magazin;
        this.reducere = reducere;
    }
    @Ignore
    public CodPromotional(String codPromotional, String magazin, Double reducere) {
        this.codPromotional = codPromotional;
        this.magazin = magazin;
        this.reducere = reducere;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodPromotional() {
        return codPromotional;
    }

    public void setCodPromotional(String codPromotional) {
        this.codPromotional = codPromotional;
    }

    public String getMagazin() {
        return magazin;
    }

    public void setMagazin(String magazin) {
        this.magazin = magazin;
    }

    public Double getReducere() {
        return reducere;
    }

    public void setReducere(Double reducere) {
        this.reducere = reducere;
    }

    @Override
    public String toString() {
        return "CodPromotional{" +
                "id=" + id +
                ", codPromotional='" + codPromotional + '\'' +
                ", magazin='" + magazin + '\'' +
                ", reducere=" + reducere +
                '}';
    }
}