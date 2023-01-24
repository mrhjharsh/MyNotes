package com.example.notesapplicationjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import io.realm.Realm;
import io.realm.RealmResults;

public class newNote extends AppCompatActivity {
    public static Realm realm;
    public  static RealmResults<Task> notes;

    @Override
    public void onBackPressed() {
        Intent intent;
        intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        finish();
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
Realm.init(getApplicationContext());
Realm realm = Realm.getDefaultInstance();


        setContentView(R.layout.activity_new_note);
        Button b = findViewById(R.id.button2);
        EditText e1 = findViewById(R.id.city);
        EditText e2 = findViewById(R.id.country);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(e1.getText().toString().isEmpty() || e2.getText().toString().isEmpty())
                {
                    Toast.makeText(newNote.this , "PLEASE MENTION BOTH TITLE AND DESCRIPTION" , Toast.LENGTH_SHORT).show();

                }else{
                    realm.beginTransaction();
                    Task task = realm.createObject(Task.class);
                    task.setTitle(e1.getText().toString());
                    task.setDescription(e2.getText().toString());
realm.commitTransaction();

                   // DATABASE db = new DATABASE(e1.getText().toString() , e2.getText().toString() );
                    DATABASE.l1.clear();
                    DATABASE.l2.clear();
                     notes = realm.where(Task.class).findAll();


                    for (int i = 0; i < notes.size(); i++) {
                        DATABASE.l1.add(notes.get(i).getTitle());
                        DATABASE.l2.add(notes.get(i).getDescription());
                    }
                    startActivity(new Intent(newNote.this,MainActivity.class));
                    Toast.makeText(newNote.this , "NOTE SAVED" , Toast.LENGTH_SHORT).show();
                    finish();
                    //change in realm edit
                }
            }
        });

    }

}