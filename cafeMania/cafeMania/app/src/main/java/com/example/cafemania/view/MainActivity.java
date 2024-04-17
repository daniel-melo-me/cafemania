package com.example.cafemania.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cafemania.R;
import com.example.cafemania.view.CoffeeListActivity;
import com.example.cafemania.view.CoffeeRegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Método para iniciar a Activity de cadastro de cafés
    public void registerCoffee(View view) {
        Intent intent = new Intent(this, CoffeeRegisterActivity.class);
        startActivity(intent);
    }

    // Método para iniciar a Activity de listagem de cafés
    public void listCoffees(View view) {
        Intent intent = new Intent(this, CoffeeListActivity.class);
        startActivity(intent);
    }
}
