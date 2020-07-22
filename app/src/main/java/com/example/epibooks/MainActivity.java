package com.example.epibooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

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
        test();





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
    public void SetSwitcher(final String filter) {
        listBook.setAdapter(null);
        if (filter == "Fav")
            listBook.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, fav));
        else if (filter == "ToRead")
            listBook.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, toRead));
        else if (filter == "Reading")
            listBook.setAdapter(new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, reading));
        listBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (filter == "Fav") {
                    String ClickedFav = parent.getItemAtPosition(position).toString();
                    for (int i = 0; i < fav.size();i++) {
                        if (fav.get(i).toString() == ClickedFav)
                            Toast.makeText(getApplicationContext(), fav.get(i).toString() ,  Toast.LENGTH_SHORT).show();
                    }
                }
                if (filter == "ToRead") {
                    String ClickedToRead = parent.getItemAtPosition(position).toString();
                    for (int x = 0; x < toRead.size();x++) {
                        if (toRead.get(x).toString() == ClickedToRead)
                            Toast.makeText(getApplicationContext(), toRead.get(x).toString() ,  Toast.LENGTH_SHORT).show();
                    }
                }
                if (filter == "Reading") {
                    String ClickedReading = parent.getItemAtPosition(position).toString();
                    for (int y = 0; y < reading.size();y++) {
                        if (reading.get(y).toString() == ClickedReading)
                            Toast.makeText(getApplicationContext(), reading.get(y).toString() ,  Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

}
