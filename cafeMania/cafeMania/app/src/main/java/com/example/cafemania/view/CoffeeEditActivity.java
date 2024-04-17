package com.example.cafemania.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.cafemania.Coffee;
import com.example.cafemania.R;
import com.example.cafemania.model.CoffeeViewModel;

public class CoffeeEditActivity extends AppCompatActivity {
    private EditText editCoffeeName;
    private EditText editCoffeeDescription;
    private Button buttonSave;
    private CoffeeViewModel coffeeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_edit);

        // Inicializando os componentes da UI
        editCoffeeName = findViewById(R.id.edit_coffee_name);
        editCoffeeDescription = findViewById(R.id.edit_coffee_description);
        buttonSave = findViewById(R.id.button_save);

        // Configurando o ViewModel
        coffeeViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication())).get(CoffeeViewModel.class);

        // Carregar dados do café baseado no ID passado
        int coffeeId = getIntent().getIntExtra("coffee_id", -1);
        if (coffeeId == -1) {
            Log.e("CoffeeEditActivity", "Id do café inválido.");
        }

        if (coffeeId != -1) {
            coffeeViewModel.getCoffeeById(coffeeId).observe(this, coffee -> {
                if (coffee != null) {
                    editCoffeeName.setText(coffee.getName());
                    editCoffeeDescription.setText(coffee.getDescription());
                } else {
                    Log.e("CoffeeEditActivity", "Nenhum café encontrado com o id: " + coffeeId);
                }
            });
        }

        // Definindo o listener para o botão salvar
        buttonSave.setOnClickListener(v -> {
            saveCoffee(coffeeId);
        });
    }

    private void saveCoffee(int coffeeId) {
        String name = editCoffeeName.getText().toString().trim();
        String description = editCoffeeDescription.getText().toString().trim();

        if (!name.isEmpty() && !description.isEmpty()) {
            Coffee coffee = new Coffee(name, description); // Supondo que Coffee tenha esse construtor.
            coffee.setId(coffeeId);

            // Salvando o café via ViewModel
            coffeeViewModel.update(coffee);

            // Fechar a atividade após salvar
            finish();
        } else {
            // Mostrar erro se os campos estiverem vazios
            editCoffeeName.setError("Name cannot be empty");
            editCoffeeDescription.setError("Description cannot be empty");
        }
    }
}
