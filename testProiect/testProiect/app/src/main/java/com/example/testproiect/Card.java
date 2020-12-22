package com.example.testproiect;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="Carduri")
public class Card {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="Nume")
        private String nume;
        private String idCard;
        private String dataEliberareCard;

    public Card(String nume, String idCard, String dataEliberareCard) {
        this.nume = nume;
        this.idCard = idCard;
        this.dataEliberareCard = dataEliberareCard;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getDataEliberareCard() {
        return dataEliberareCard;
    }

    public void setDataEliberareCard(String dataEliberareCard) {
        this.dataEliberareCard = dataEliberareCard;
    }

    @Override
    public String toString() {
        return "Carddul cu " +
                "id-ul" + id +
                ", are numele " + nume + '\'' +
                ", numarul " + idCard + '\'' +
                ", si a fost eliberat in data de " + dataEliberareCard +
                '.';
    }
}
