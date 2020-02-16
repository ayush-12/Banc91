package com.assignment.banc91.SqlLiteDb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {CardDetailsSql.class},version = 1)
public abstract class CardDetailsSqlDatabase extends RoomDatabase {

    public abstract CardDetailsSqlDAO cardDetailsSqlDAO();
}
