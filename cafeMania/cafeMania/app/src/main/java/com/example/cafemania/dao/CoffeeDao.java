package com.example.cafemania.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cafemania.Coffee;

import java.util.List;

@Dao
public interface CoffeeDao {

    @Insert
    void insert(Coffee coffee);

    @Update
    void update(Coffee coffee);

    @Delete
    void delete(Coffee coffee);

    @Query("SELECT * FROM Coffee ORDER BY name ASC")
    LiveData<List<Coffee>> getAllCoffees();

    @Query("SELECT * FROM Coffee WHERE id = :id")
    LiveData<Coffee> getCoffeeById(int id);
}
