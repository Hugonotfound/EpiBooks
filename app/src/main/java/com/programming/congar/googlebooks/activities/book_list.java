package com.programming.congar.googlebooks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.programming.congar.googlebooks.R;
import com.programming.congar.googlebooks.adapters.RecyclerViewAdapter;
import com.programming.congar.googlebooks.model.Book;

import java.util.List;

public class book_list extends AppCompatActivity {

    private TextView favorite_tv;
    private TextView toread_tv;
    private TextView reading_tv;

    private RecyclerView favoriteRecyclerView;
    private RecyclerView readingRecyclerView;
    private RecyclerView toreadRecyclerView;
    private RecyclerViewAdapter favoriteStoredRecyclerViewAdapter;
    private RecyclerViewAdapter readingStoredRecyclerViewAdapter;
    private RecyclerViewAdapter toreadStoredRecyclerViewAdapter;

    private List<Book> books;
    private List<Book> books1;
    private List<Book> books2;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        ImageButton toread_button = findViewById(R.id.dispToread_button);
        ImageButton reading_button = findViewById(R.id.dispReading_button);
        ImageButton favorite_button = findViewById(R.id.dispFavorite_button);
        favoriteRecyclerView = findViewById(R.id.favorite_recycler_view);
        readingRecyclerView = findViewById(R.id.reading_recycler_view);
        toreadRecyclerView = findViewById(R.id.toread_recycler_view);

        favorite_tv = findViewById(R.id.favorite_tv);
        reading_tv = findViewById(R.id.reading_tv);
        toread_tv = findViewById(R.id.toread_tv);

        favoriteRecyclerView.setHasFixedSize(true);
        readingRecyclerView.setHasFixedSize(true);
        toreadRecyclerView.setHasFixedSize(true);
        favoriteRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        readingRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toreadRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = new Database(this);
        books = database.readBooks("FAVORITE");
        books1 = database.readBooks("READING");
        books2 = database.readBooks("TOREAD");
        favoriteStoredRecyclerViewAdapter = new RecyclerViewAdapter(book_list.this , books);
        readingStoredRecyclerViewAdapter = new RecyclerViewAdapter(book_list.this , books1);
        toreadStoredRecyclerViewAdapter = new RecyclerViewAdapter(book_list.this , books2);
        favoriteRecyclerView.setAdapter(favoriteStoredRecyclerViewAdapter);
        readingRecyclerView.setAdapter(readingStoredRecyclerViewAdapter);
        toreadRecyclerView.setAdapter(toreadStoredRecyclerViewAdapter);

        favorite_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteRecyclerView.setVisibility(View.VISIBLE);
                readingRecyclerView.setVisibility(View.INVISIBLE);
                toreadRecyclerView.setVisibility(View.INVISIBLE);
                favorite_tv.setVisibility(View.VISIBLE);
                reading_tv.setVisibility(View.INVISIBLE);
                toread_tv.setVisibility(View.INVISIBLE);
            }
        });

        toread_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteRecyclerView.setVisibility(View.INVISIBLE);
                readingRecyclerView.setVisibility(View.INVISIBLE);
                toreadRecyclerView.setVisibility(View.VISIBLE);
                favorite_tv.setVisibility(View.INVISIBLE);
                reading_tv.setVisibility(View.INVISIBLE);
                toread_tv.setVisibility(View.VISIBLE);
            }
        });

        reading_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteRecyclerView.setVisibility(View.INVISIBLE);
                readingRecyclerView.setVisibility(View.VISIBLE);
                toreadRecyclerView.setVisibility(View.INVISIBLE);
                favorite_tv.setVisibility(View.INVISIBLE);
                reading_tv.setVisibility(View.VISIBLE);
                toread_tv.setVisibility(View.INVISIBLE);
            }
        });
    }

}