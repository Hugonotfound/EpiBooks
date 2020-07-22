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


        btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("Favorites");
            }
        });
        btn_reading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("Reading");
            }
        });
        btn_toRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                text.setText("To Read");
            }
        });






    }

    public void test () {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://www.googleapis.com/books/v1/volumes?q=Harry+Potter";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(getApplicationContext(), "response" ,  Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "pas ok" ,  Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
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