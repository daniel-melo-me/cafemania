package com.example.cafemania.repository;

import android.os.AsyncTask;

import com.example.cafemania.Coffee;
import com.example.cafemania.dao.CoffeeDao;

public class UpdateCoffeeAsyncTask extends AsyncTask<Coffee, Void, Void> {
    private CoffeeDao coffeeDao;

    public UpdateCoffeeAsyncTask(CoffeeDao coffeeDao) {
        this.coffeeDao = coffeeDao;
    }

    @Override
    protected Void doInBackground(Coffee... coffees) {
        coffeeDao.update(coffees[0]);
        return null;
    }
}
