package com.example.travel4.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Trip.class}, version = 1, exportSchema = false)
public abstract class TripDataBase extends RoomDatabase {
    private static TripDataBase dataBase;

    public final static String DATABASE_NAME = "database";

    public synchronized static TripDataBase getInstance(Context context) {
        if (dataBase == null) {
            dataBase = Room.databaseBuilder(context.getApplicationContext(), TripDataBase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return dataBase;
    }


    public abstract TripDao getTripDao();
}