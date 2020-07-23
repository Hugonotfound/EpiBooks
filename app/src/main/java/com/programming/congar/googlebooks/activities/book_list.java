package com.programming.congar.googlebooks.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.programming.congar.googlebooks.R;
import com.programming.congar.googlebooks.adapters.RecyclerViewAdapter;
import com.programming.congar.googlebooks.model.Book;

import java.util.List;

public class book_list extends AppCompatActivity {

    private RecyclerView storedRecyclerView;
    private RecyclerViewAdapter storedRecyclerViewAdapter;
    private List<Book> books;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        storedRecyclerView = findViewById(R.id.stored_recycler_view);
        storedRecyclerView.setHasFixedSize(true);
        storedRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        database = new Database(this);

        books = database.readBooks();
        storedRecyclerViewAdapter = new RecyclerViewAdapter(book_list.this , books);
        storedRecyclerView.setAdapter(storedRecyclerViewAdapter);
    }

}