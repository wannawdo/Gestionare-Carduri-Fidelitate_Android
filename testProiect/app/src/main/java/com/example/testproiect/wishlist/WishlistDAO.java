package com.example.testproiect.wishlist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WishlistDAO {
    @Query("select * from wishlist")
    List<Wishlist> getAll();

    @Insert
    long insert(Wishlist wishlist);
    //intoarce id-ul inregistrarii sau -1 daca apar probleme

    @Update
    int update(Wishlist wishlist);
    //int-> nr de randuri afectate

    @Delete
    int delete(Wishlist wishlist);
    // -1 = eroare de sql
    // int-> nr de randuri sterse
}
