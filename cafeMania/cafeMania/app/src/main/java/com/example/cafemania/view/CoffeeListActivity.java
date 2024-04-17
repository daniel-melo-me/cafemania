package com.example.cafemania.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafemania.Coffee;
import com.example.cafemania.R;
import com.example.cafemania.adapter.CoffeeAdapter;
import com.example.cafemania.model.CoffeeViewModel;

public class CoffeeListActivity extends AppCompatActivity implements CoffeeAdapter.OnCoffeeDeleteListener {

    private CoffeeViewModel coffeeViewModel;
    private CoffeeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_list);

        // Habilitando a seta de retorno na ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        adapter = new CoffeeAdapter(this, this);  // Aqui está a mudança
        recyclerView.setAdapter(adapter);

        coffeeViewModel = new ViewModelProvider(this).get(CoffeeViewModel.class);
        coffeeViewModel.getAllCoffees().observe(this, coffees -> adapter.setCoffees(coffees));
    }

    @Override
    public void onDeleteCoffee(Coffee coffee) {
        coffeeViewModel.delete(coffee);
    }
}
