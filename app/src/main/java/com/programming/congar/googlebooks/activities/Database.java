package com.programming.congar.googlebooks.activities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.programming.congar.googlebooks.model.Book;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "EpibooksDB.db";
    private static final int DATABASE_VERSION = 1;
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strSql = "create Table T_Favorite ("
                + "     idBook integer primary key autoincrement,"
                + "     title text not null,"
                + "     author text not null,"
                + "     description text not null,"
                + "     category text not null,"
                + "     imgLink text not null"
                + ")";
        db.execSQL(strSql);

        String strSql1 = "create Table T_Toread ("
                + "     idBook integer primary key autoincrement,"
                + "     title text not null,"
                + "     author text not null,"
                + "     description text not null,"
                + "     category text not null,"
                + "     imgLink text not null"
                + ")";
        db.execSQL(strSql1);

        String strSql2 = "create Table T_Reading ("
                + "     idBook integer primary key autoincrement,"
                + "     title text not null,"
                + "     author text not null,"
                + "     description text not null,"
                + "     category text not null,"
                + "     imgLink text not null"
                + ")";
        db.execSQL(strSql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String strSql = "drop table T_Books";
        db.execSQL(strSql);
        String strSql1 = "drop table T_Favorite";
        db.execSQL(strSql1);
        String strSql2 = "drop table T_Toread";
        db.execSQL(strSql2);
        String strSql3 = "drop table T_Reading";
        db.execSQL(strSql3);
        this.onCreate(db);
    }

    public void insertBook(Book book, String cat) {
        String title = book.getTitle();
        String author = book.getAuthors();
        String description = book.getDescription();
        String category = book.getCategories();
        String imgLink = book.getThumbnail();
        String table;

        if (cat.equalsIgnoreCase("TOREAD"))
            table = "T_Toread";
        else if (cat.equalsIgnoreCase("FAVORITE"))
            table = "T_Favorite";
        else if (cat.equalsIgnoreCase("READING"))
            table = "T_Reading";
        else
            table = "T_Favorite";


        title = title.replace("'", "''");
        author = author.replace("'", "''");
        description = description.replace("'", "''");
        imgLink = imgLink.replace("'", "''");

        String strSql = "insert into "+ table +" (title, author, description, category, imgLink) values ('"
                + title + "', '" + author + "', '" + description + "', '" + category + "', '" + imgLink + "')";


        this.getWritableDatabase().execSQL(strSql);
    }

    public List<Book> readBooks(String cat) {
        String table;

        if (cat.equalsIgnoreCase("TOREAD"))
            table = "T_Toread";
        else if (cat.equalsIgnoreCase("FAVORITE"))
            table = "T_Favorite";
        else if (cat.equalsIgnoreCase("READING"))
            table = "T_Reading";
        else
            table = "T_Favorite";
        List<Book> books = new ArrayList<>();
        String strSql = "select * from "+ table +" order by idBook";
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

    public Boolean checkBook(String title, String cat) {
        String table;

        if (cat.equalsIgnoreCase("TOREAD"))
            table = "T_Toread";
        else if (cat.equalsIgnoreCase("FAVORITE"))
            table = "T_Favorite";
        else if (cat.equalsIgnoreCase("READING"))
            table = "T_Reading";
        else
            table = "T_Favorite";
        String strSql = "select * from "+ table +" order by idBook";
        Cursor cursor = this.getReadableDatabase().rawQuery(strSql, null);
        cursor.moveToFirst();


        while ( !cursor.isAfterLast()) {
            Log.i("DEBUG", "checkBook: " + cursor.getString(1));
            if (cursor.getString(1).equalsIgnoreCase(title)) {
                Log.i("DEBUG", "DANS LES FAVORIES");
                cursor.close();
                return true;
            }
            cursor.moveToNext();
        }
        cursor.close();
        Log.i("DEBUG", "PAS DANS LES FAVORIES");
        return false;
    }

    public void deleteBook(Book book, String cat) {
        String table;

        if (cat.equalsIgnoreCase("TOREAD"))
            table = "T_Toread";
        else if (cat.equalsIgnoreCase("FAVORITE"))
            table = "T_Favorite";
        else if (cat.equalsIgnoreCase("READING"))
            table = "T_Reading";
        else
            table = "T_Favorite";
        String strSql = "delete from "+ table +" where title='" + book.getTitle().replace("'", "''") + "'";
        this.getWritableDatabase().execSQL(strSql);
    }

}
