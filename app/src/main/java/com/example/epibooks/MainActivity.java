package com.example.epibooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public ArrayList<Object> fav = new ArrayList<Object>();
    public ArrayList<Object> reading = new ArrayList<Object>();
    public ArrayList<Object> toRead = new ArrayList<Object>();
    public ArrayList<Object> global = new ArrayList<>();
    public ListView listBook;
    public static String search_id;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //set Front interact//
        listBook = findViewById(R.id.List_Book);
        final EditText inputSearch = findViewById(R.id.Input_Book);
        ImageButton btn_search = findViewById(R.id.Btn_Search);


        for (int i = 0; i < 100; i++) {
            fav.add(i);
        }
        for (int i = 100; i < 200; i++) {
            reading.add(i);
        }
        for (int i = 200; i < 300; i++) {
            toRead.add(i);
        }
        SetArrayForListView();
        listBook.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, global));
        listBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Clicked = parent.getItemAtPosition(position).toString();
                for (int i = 0; i < global.size();i++) {
                    if (global.get(i).toString() == Clicked) {
                        Toast.makeText(getApplicationContext(), global.get(i).toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_id = inputSearch.getText().toString();
                Intent searchActivity =  new Intent(getApplicationContext() , SearchActivity.class);
                startActivity(searchActivity);
                onPause();
            }
        });




    }


    public void SetArrayForListView() {
        global.add("===Favorites===");
        for(int i = 0; i < fav.size(); i++)
            global.add(fav.get(i).toString());
        global.add("===To Read===");
        for(int i = 0; i < toRead.size(); i++)
            global.add(toRead.get(i).toString());
        global.add("===Reading===");
        for(int i = 0; i < reading.size(); i++)
            global.add(reading.get(i).toString());
    }

}
/*

 listBook.setAdapter(null);
        if (filter == "Fav")
            listBook.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, fav));
        else if (filter == "ToRead")
            listBook.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, toRead));
        else if (filter == "Reading")
            listBook.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, reading));

 */