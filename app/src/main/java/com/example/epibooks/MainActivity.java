package com.example.epibooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Object> fav = new ArrayList<Object>();
    public ArrayList<Object> reading = new ArrayList<Object>();
    public ArrayList<Object> toRead = new ArrayList<Object>();
    public ListView listBook;
    public String Selector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set Front interact//
        listBook = findViewById(R.id.List_Book);
        EditText inputSearch = findViewById(R.id.Input_Book);
        ImageButton btn_search = findViewById(R.id.Btn_Search);
        ImageButton btn_fav = findViewById(R.id.Btn_Fav);
        ImageButton btn_reading = findViewById(R.id.Btn_Reading);
        ImageButton btn_toRead = findViewById(R.id.Btn_ToRead);
        final TextView text = findViewById(R.id.Title_List);

        for (int i = 0; i < 100; i++) {
            fav.add(i);
        }
        for (int i = 100; i < 200; i++) {
            reading.add(i);
        }
        for (int i = 200; i < 300; i++) {
            toRead.add(i);
        }

        listBook.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, fav));
        listBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String testName = parent.getItemAtPosition(position).toString();
                for (int i = 0; i < fav.size();i++) {
                    if (fav.get(i).toString() == testName)
                        Toast.makeText(getApplicationContext(), fav.get(i).toString() ,  Toast.LENGTH_SHORT).show();

                }
            }
        });


        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetSwitcher("Fav");
                text.setText("Favorites");
            }
        });
        btn_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetSwitcher("Reading");
                text.setText("Reading");
            }
        });
        btn_toRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SetSwitcher("ToRead");
                text.setText("To Read");
            }
        });






    }


    public void SetSwitcher(String filter) {
        if (filter == "Fav") {
            listBook.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, fav));
            listBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String testName = parent.getItemAtPosition(position).toString();
                    for (int i = 0; i < fav.size();i++) {
                        if (fav.get(i).toString() == testName)
                            Toast.makeText(getApplicationContext(), fav.get(i).toString() ,  Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        else if (filter == "ToRead") {
            listBook.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, toRead));
            listBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String testName = parent.getItemAtPosition(position).toString();
                    for (int i = 0; i < toRead.size();i++) {
                        if (toRead.get(i).toString() == testName)
                            Toast.makeText(getApplicationContext(), toRead.get(i).toString() ,  Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
        else if (filter == "Reading") {
            listBook.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, reading));
            listBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String testName = parent.getItemAtPosition(position).toString();
                    for (int i = 0; i < reading.size();i++) {
                        if (reading.get(i).toString() == testName)
                            Toast.makeText(getApplicationContext(), reading.get(i).toString() ,  Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

}