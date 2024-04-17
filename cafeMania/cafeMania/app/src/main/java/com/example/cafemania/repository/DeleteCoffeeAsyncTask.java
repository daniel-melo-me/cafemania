package com.example.cafemania.repository;

import android.os.AsyncTask;

import com.example.cafemania.Coffee;
import com.example.cafemania.dao.CoffeeDao;

public class DeleteCoffeeAsyncTask extends AsyncTask<Coffee, Void, Void> {
    private CoffeeDao coffeeDao;

    DeleteCoffeeAsyncTask(CoffeeDao coffeeDao) {
        this.coffeeDao = coffeeDao;
    }

    @Override
    protected Void doInBackground(Coffee... coffees) {
        coffeeDao.delete(coffees[0]);
        return null;
    }
}
