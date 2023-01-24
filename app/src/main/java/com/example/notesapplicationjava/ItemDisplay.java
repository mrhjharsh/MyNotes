package com.example.notesapplicationjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import io.realm.Realm;
import io.realm.RealmResults;

public class ItemDisplay extends AppCompatActivity {
Realm realm;
public  static RealmResults<Task> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        setContentView(R.layout.item_display);
        EditText tit = findViewById(R.id.city);
        EditText desc = findViewById(R.id.country);
tit.setText(DATABASE.l1.get(MainActivity.pos));
desc.setText(DATABASE.l2.get(MainActivity.pos));
        Button b = findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ///////////////////////////////edit note
                realm.beginTransaction();
                notes = realm.where(Task.class).findAll();
                Task t = notes.get(MainActivity.pos);
                t.setTitle( tit.getText().toString());
                t.setDescription(desc.getText().toString());
                realm.commitTransaction();
                ////////////////////////////////////////////////
                DATABASE.l1.set(MainActivity.pos , tit.getText().toString());
                DATABASE.l2.set(MainActivity.pos , desc.getText().toString());
                startActivity(new Intent(ItemDisplay.this,MainActivity.class));
            }
        });
    }
}