package com.example.testproiect;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDAO {
    @Insert
    void inserareCardInDB(Card card);

    @Query("SELECT * FROM Carduri")
    List<Card> selectAllCards();

    @Query("SELECT * FROM Carduri WHERE nume=:nume LIMIT 1")
    Card getCardDupaNume(String nume);
}
