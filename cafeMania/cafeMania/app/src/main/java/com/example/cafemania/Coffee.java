package com.example.cafemania;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Coffee {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String description;

    // Construtor com argumentos
    public Coffee(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Coffee() {

    }


    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
