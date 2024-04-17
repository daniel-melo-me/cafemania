package com.example.cafemania.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.cafemania.Coffee;
import com.example.cafemania.R;
import com.example.cafemania.model.CoffeeViewModel;

public class CoffeeRegisterActivity extends AppCompatActivity {

    private EditText editTextName, editTextDescription;
    private Button buttonSave;
    private CoffeeViewModel coffeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_register);

        coffeeViewModel = new ViewModelProvider(this).get(CoffeeViewModel.class);

        editTextName = findViewById(R.id.editTextCoffeeName);
        editTextDescription = findViewById(R.id.editTextCoffeeDescription);
        buttonSave = findViewById(R.id.buttonSaveCoffee);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveCoffee();
            }
        });
    }

    private void saveCoffee() {
        String name = editTextName.getText().toString();
        String description = editTextDescription.getText().toString();

        if (name.trim().isEmpty() || description.trim().isEmpty()) {
            // Mostrar alguma mensagem de erro ou fazer a validação conforme necessário
            return;
        }

        Coffee coffee = new Coffee(name, description);
        coffeeViewModel.insert(coffee);
        finish();
    }
}
