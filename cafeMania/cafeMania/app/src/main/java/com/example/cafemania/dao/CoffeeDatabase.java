package com.example.cafemania.dao;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cafemania.Coffee;

@Database(entities = {Coffee.class}, version = 2)
public abstract class CoffeeDatabase extends RoomDatabase {

    private static CoffeeDatabase instance;

    public abstract CoffeeDao coffeeDao();

    public static synchronized CoffeeDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            CoffeeDatabase.class, "coffee_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
