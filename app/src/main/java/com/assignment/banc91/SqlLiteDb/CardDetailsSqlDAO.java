package com.assignment.banc91.SqlLiteDb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CardDetailsSqlDAO {

    @Insert
    void addNoteToDB(CardDetailsSql cardDetailsSql);

    @Query("SELECT * FROM CardDetailsSql ")
    public List<CardDetailsSql> getAllData();

    @Query("SELECT cardNumber FROM carddetailssql group by cardNumber")
    List<String> fetchDistinctCard();

    @Query("SELECT * FROM CardDetailsSql Where cardNumber =:cardNumber ")
    public List<CardDetailsSql> getData(String cardNumber);

}

