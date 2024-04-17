package com.example.cafemania.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cafemania.Coffee;
import com.example.cafemania.R;
import com.example.cafemania.view.CoffeeEditActivity;

import java.util.ArrayList;
import java.util.List;

public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.CoffeeHolder> {
    private List<Coffee> coffees;
    private Context context;
    private OnCoffeeDeleteListener deleteListener;

    public CoffeeAdapter(Context context, OnCoffeeDeleteListener deleteListener) {
        this.coffees = new ArrayList<>();
        this.context = context;
        this.deleteListener = deleteListener;
    }

    @NonNull
    @Override
    public CoffeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coffee_item, parent, false);
        return new CoffeeHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CoffeeHolder holder, int position) {
        Coffee currentCoffee = coffees.get(position);
        holder.textViewName.setText(currentCoffee.getName());
        holder.textViewDescription.setText(currentCoffee.getDescription());
        holder.buttonDelete.setOnClickListener(v -> showDeleteDialog(currentCoffee));
        holder.buttonEdit.setOnClickListener(v -> openEditScreen(currentCoffee));
        holder.buttonShare.setOnClickListener(v -> shareCoffeeDetails(currentCoffee));
    }

    private void openEditScreen(Coffee currentCoffee) {
        Intent intent = new Intent(context, CoffeeEditActivity.class);
        intent.putExtra("coffee_id", currentCoffee.getId());
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return coffees.size();
    }

    public void setCoffees(List<Coffee> coffees) {
        this.coffees = coffees != null ? coffees : new ArrayList<>();  // Garantir que a lista nunca será nula
        notifyDataSetChanged();
    }

    private void showDeleteDialog(Coffee coffee) {
        new AlertDialog.Builder(context)
                .setTitle(R.string.confirm_delete)
                .setMessage(R.string.are_you_sure)
                .setPositiveButton(R.string.delete, (dialog, which) -> deleteCoffee(coffee))
                .setNegativeButton(android.R.string.cancel, null)
                .show();
    }

    private void deleteCoffee(Coffee coffee) {
        if (deleteListener != null) {
            deleteListener.onDeleteCoffee(coffee);
            Toast.makeText(context, R.string.delete_success, Toast.LENGTH_SHORT).show();
        }
    }

    private void shareCoffeeDetails(Coffee coffee) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareBody = "Check out this coffee: " + coffee.getName() + ", " + coffee.getDescription();
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Details");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        context.startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    class CoffeeHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewDescription;
        private ImageButton buttonDelete, buttonEdit, buttonShare;

        public CoffeeHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewDescription = itemView.findViewById(R.id.text_view_description);
            buttonDelete = itemView.findViewById(R.id.button_delete);
            buttonEdit = itemView.findViewById(R.id.button_edit);
            buttonShare = itemView.findViewById(R.id.button_share);
        }
    }



    public interface OnCoffeeDeleteListener {
        void onDeleteCoffee(Coffee coffee);
    }
}
