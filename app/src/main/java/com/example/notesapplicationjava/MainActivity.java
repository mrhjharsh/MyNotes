package com.example.notesapplicationjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
static int pos = 0;
    public static Realm realm;
    public  static RealmResults<Task> notes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().hide();
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button b = findViewById(R.id.button);
        ImageView click = findViewById(R.id.click);
        ImageView iv = findViewById(R.id.arrow);

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();
        notes = realm.where(Task.class).findAll();
        DATABASE.l1.clear();
        DATABASE.l2.clear();
        for (int i = 0; i < notes.size(); i++) {
            DATABASE.l1.add(notes.get(i).getTitle());
            DATABASE.l2.add(notes.get(i).getDescription());
        }
        if(DATABASE.l1.size() > 0){
            click.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.INVISIBLE);
        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
finish();
                startActivity(new Intent(MainActivity.this,newNote.class));

            }
        });
        RecyclerView rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        //rv.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
        CustomAdapter ca = new CustomAdapter();
        rv.setAdapter(ca);
        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(MainActivity.this, rv ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        pos = position;
                     startActivity(new Intent(MainActivity.this , display.class));
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        pos = position;

                        //////////////////////popup menu
                        PopupMenu menu = new PopupMenu(MainActivity.this,view);
                        menu.getMenu().add("EDIT");
                        menu.getMenu().add("DELETE");
                        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                if(item.getTitle().equals("EDIT")){
                                    startActivity(new Intent(MainActivity.this,ItemDisplay.class));

                                }
                                if(item.getTitle().equals("DELETE")){
                                    DATABASE.l1.remove(pos);
                                    DATABASE.l2.remove(pos);
//////////////////////////////////////////////////////////////////deleting note
                                    Task note = notes.get(pos);
                                    realm.beginTransaction();
                                    note.deleteFromRealm();
                                    realm.commitTransaction();
                                    Toast.makeText(MainActivity.this , "NOTE DELETED" , Toast.LENGTH_SHORT).show();

                                    /////////////////////////////////////////////////////////////////////////
                                    Intent i = new Intent(MainActivity.this, MainActivity.class);
                                    finish();
                                    overridePendingTransition(0, 0);
                                    startActivity(i);
                                    overridePendingTransition(0, 0);

                                }
                                return true;
                            }
                        });
                        menu.show();
                        /////////////////////////////////////////////////////////


                    }
                })
        );

    }

}