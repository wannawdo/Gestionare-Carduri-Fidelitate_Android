package com.example.testproiect;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities={Card.class},version=1,exportSchema = false)
public abstract class CardDataBase extends RoomDatabase {
    public abstract CardDAO getCardDAO();

}
