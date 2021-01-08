package com.example.testproiect.wishlist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.testproiect.card.DateConverter;
import com.example.testproiect.coduri.promotionale.CodPromotional;
import com.example.testproiect.coduri.promotionale.CodPromotionalDAO;

@Database(entities = {Wishlist.class, CodPromotional.class}, exportSchema = false, version = 2)


public abstract class DatabaseManager extends RoomDatabase {
    private static final String DAM_DB = "dam_db";
    private static DatabaseManager databaseManager;

    public static DatabaseManager getInstance(Context context) {
        if (databaseManager == null) {
            synchronized (DatabaseManager.class) {
                if (databaseManager == null) {
                    databaseManager = Room.databaseBuilder(context, DatabaseManager.class, DAM_DB)
                            .fallbackToDestructiveMigration()//pentru modificari asupra tebeleiș ce sa faca daca nu poate migra modificarile
                            .build();
                }
            }
        }
        return databaseManager;
    }

    public abstract WishlistDAO getWishlistDao();
    public abstract CodPromotionalDAO getCodPromotionalDao();

}
