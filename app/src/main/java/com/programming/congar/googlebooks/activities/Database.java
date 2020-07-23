package com.programming.congar.googlebooks.activities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.programming.congar.googlebooks.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Epibooks.db";
    private static final int DATABASE_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "create Table T_Books ("
                + "     idBook integer primary key autoincrement,"
                + "     title text not null,"
                + "     author text not null,"
                + "     description text not null,"
                + "     category text not null,"
                + "     imgLink text not null"
                + ")";
        db.execSQL(strSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String strSql = "drop table T_Books";
        db.execSQL(strSql);
        this.onCreate(db);
    }

    public void insertBook(Book book) {
        String title = book.getTitle();
        String author = book.getAuthors();
        String description = book.getDescription();
        String category = book.getCategories();
        String imgLink = book.getThumbnail();
        String strSql = "insert into T_Books (title, author, description, category, imgLink) values ('"
                + title + "', '" + author + "', '" + description + "', '" + category + "', '" + imgLink + "')";

        this.getWritableDatabase().execSQL(strSql);
    }

    public List<Book> readBooks() {
        List<Book> books = new ArrayList<>();
        String strSql = "select * from T_Books order by idBook";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();

        while ( !cursor.isAfterLast()) {
            Book book = new Book(cursor.getString(1), cursor.getString(2), "none", cursor.getString(3), cursor.getString(4), cursor.getString(5), "null,", "null", "null", 0, "null");
            books.add((book));
            cursor.moveToNext();
        }
        cursor.close();
        return(books);
    }

}
