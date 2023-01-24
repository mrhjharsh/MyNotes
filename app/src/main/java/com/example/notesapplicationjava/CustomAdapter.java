package com.example.notesapplicationjava;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{
    static String[] text = new String[100];


    CustomAdapter(){
        for (int i = 0; i < DATABASE.l1.size(); i++) {
            text[i] = "     "+ DATABASE.l1.get(i);
        }

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_design, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txt.setText(text[position]);
    }

    @Override
    public int getItemCount() {
        return DATABASE.l1.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt = itemView.findViewById(R.id.textView);
        }
    }
}
