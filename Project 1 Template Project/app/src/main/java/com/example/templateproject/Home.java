package com.example.templateproject;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        String[] products = new String[]{"Yamaha Keyboard PSRS750", "Yamaha Keyboard PSRS770", "Yamaha Guitar F310", "Yamaha Guitar F370",
        "Yamaha Guitar", "Flute", "Harmonica", "Yamaha Keyboard PSRS750", "Yamaha Keyboard PSRS770", "Yamaha Guitar F310", "Yamaha Guitar F370",
                "Yamaha Guitar", "Flute", "Harmonica"};

        ListAdapter listAdapter = new ArrayAdapter<String>(this,R.layout.list_item, products);
        listView  = findViewById(R.id.list_view);

        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String product = String.valueOf(parent.getItemAtPosition(position));
                Toast.makeText(Home.this, product, Toast.LENGTH_SHORT).show();
            }
        });

    }
}