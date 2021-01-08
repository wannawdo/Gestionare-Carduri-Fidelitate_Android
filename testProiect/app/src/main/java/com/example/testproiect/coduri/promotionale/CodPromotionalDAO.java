package com.example.testproiect.coduri.promotionale;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;



import java.util.List;

@Dao
public interface CodPromotionalDAO {
    @Query("select * from coduripromotionale")
    List<CodPromotional> getAll();

    @Insert
    long insert(CodPromotional cp);
    //intoarce id-ul inregistrarii sau -1 daca apar probleme

    @Update
    int update(CodPromotional cp);
    //int-> nr de randuri afectate

    @Delete
    int delete(CodPromotional cp);
    // -1 = eroare de sql
    // int-> nr de randuri sterse
}

