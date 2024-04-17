package com.example.cafemania.model;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cafemania.Coffee;
import com.example.cafemania.repository.CoffeeRepository;

import java.util.List;

public class CoffeeViewModel extends AndroidViewModel {

    private CoffeeRepository repository;
    private LiveData<List<Coffee>> allCoffees;

    public CoffeeViewModel(@NonNull Application application) {
        super(application);
        repository = new CoffeeRepository(application);
        allCoffees = repository.getAllCoffees();
    }

    public void insert(Coffee coffee) {
        repository.insert(coffee);
    }

    public void update(Coffee coffee) {
        repository.update(coffee);
    }

    public void delete(Coffee coffee) {
        repository.delete(coffee);
    }

    public LiveData<List<Coffee>> getAllCoffees() {
        return allCoffees;
    }

    public LiveData<Coffee> getCoffeeById(int id) {
        return repository.getCoffeeById(id);
    }
}
