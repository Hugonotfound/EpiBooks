package com.programming.congar.googlebooks.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.programming.congar.googlebooks.activities.Database;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.programming.congar.googlebooks.R;
import com.programming.congar.googlebooks.model.Book;

public class BookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        final Bundle extras = getIntent().getExtras();
        String title =""
                , authors ="", description="" , categories ="", publishDate=""
                ,info ="", buy ="",preview ="" ,thumbnail ="";
        if(extras != null){
            title = extras.getString("book_title");
            authors = extras.getString("book_author");
            description = extras.getString("book_desc");
            categories = extras.getString("book_categories");
            publishDate = extras.getString("book_publish_date");
            info = extras.getString("book_info");
            buy = extras.getString("book_buy");
            preview = extras.getString("book_preview");
            thumbnail = extras.getString("book_thumbnail");
        }
        final ImageButton toRead_button = findViewById(R.id.toRead_button);
        final ImageButton reading_button = findViewById(R.id.reading_button);
        final ImageButton fav_button = findViewById(R.id.fav_button);
        final TextView tvTitle = findViewById(R.id.aa_book_name);
        final TextView tvAuthors = findViewById(R.id.aa_author);
        final TextView tvDesc = findViewById(R.id.aa_description);
        final TextView tvCatag = findViewById(R.id.aa_categorie);
        final TextView tvPublishDate = findViewById(R.id.aa_publish_date);
        Button tvInfo = findViewById(R.id.aa_info);
        Button tvPreview = findViewById(R.id.aa_preview);
        final Database database = new Database(this);
        ImageView ivThumbnail = findViewById(R.id.aa_thumbnail);

        tvTitle.setText(title);
        tvAuthors.setText(authors);
        tvDesc.setText(description);
        tvCatag.setText(categories);
        tvPublishDate.setText(publishDate);

        final String finalInfo = info;
        tvInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalInfo));
                startActivity(i);
            }
        });

        final String finalTitle = title;
        final String finalAuthors = authors;
        final String finalPublishDate = publishDate;
        final String finalDescription = description;
        final String finalCategories = categories;
        final String finalThumbnail = thumbnail;
        final String finalBuy = buy;
        final String finalPreview1 = preview;
        final String finalPreview = preview;

        tvPreview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(finalPreview));
                startActivity(i);

            }
        });

        fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Book book_post = new Book(finalTitle, finalAuthors, finalPublishDate, finalDescription, finalCategories, finalThumbnail, finalBuy, finalPreview1,"0",0,"none");
                if (database.checkBook(finalTitle, "FAVORITE") == true) {
                    Toast.makeText(getApplicationContext(), "SUPP", Toast.LENGTH_SHORT).show();
                    Log.i("DEBUG", "Suppr le livre");
                    database.deleteBook(book_post, "FAVORITE");
                }
                else {
                    Toast.makeText(getApplicationContext(), "ADD", Toast.LENGTH_SHORT).show();
                    Log.i("DEBUG", "Ajouter le livre");
                    database.insertBook(book_post, "FAVORITE");
                }
            }
        });

        reading_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book_post = new Book(finalTitle, finalAuthors, finalPublishDate, finalDescription, finalCategories, finalThumbnail, finalBuy, finalPreview1,"0",0,"none");
                if (database.checkBook(finalTitle, "READING") == true) {
                    Toast.makeText(getApplicationContext(), "SUPP", Toast.LENGTH_SHORT).show();
                    Log.i("DEBUG", "Suppr le livre");
                    database.deleteBook(book_post, "READING");
                }
                else {
                    Toast.makeText(getApplicationContext(), "ADD", Toast.LENGTH_SHORT).show();
                    Log.i("DEBUG", "Ajouter le livre");
                    database.insertBook(book_post, "READING");
                }
            }
        });

        toRead_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book_post = new Book(finalTitle, finalAuthors, finalPublishDate, finalDescription, finalCategories, finalThumbnail, finalBuy, finalPreview1,"0",0,"none");
                if (database.checkBook(finalTitle, "TOREAD") == true) {
                    Toast.makeText(getApplicationContext(), "SUPP", Toast.LENGTH_SHORT).show();
                    Log.i("DEBUG", "Suppr le livre");
                    database.deleteBook(book_post, "TOREAD");
                }
                else {
                    Toast.makeText(getApplicationContext(), "ADD", Toast.LENGTH_SHORT).show();
                    Log.i("DEBUG", "Ajouter le livre");
                    database.insertBook(book_post, "TOREAD");
                }
            }
        });

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(thumbnail).apply(requestOptions).into(ivThumbnail);
    }
}
