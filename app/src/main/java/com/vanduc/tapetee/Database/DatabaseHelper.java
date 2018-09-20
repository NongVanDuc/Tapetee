package com.vanduc.tapetee.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.vanduc.tapetee.Model.Favorite;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // database
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "wallpaper.db";
    // table name
    private static final String TBNAME = "favorite"; //
    private static final String KEY_ID = "Id";
    private static final String KEY_LINK = "Link";
    private static final String KEY_VIEW = "Countview";
    private static final String KEY_LIKE = "Countlike";

    private SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TBNAME + "(\n" +
                "\t" + KEY_ID + "\tINTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "\t" + KEY_LINK + "\tTEXT,\n" +
                "\t" + KEY_VIEW + "\tTEXT,\n" +
                "\t" + KEY_LIKE + "\tTEXT\n" +
                ");";


        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TBNAME);
        onCreate(db);
    }
    //functions
    public boolean addImage(Favorite favorite) {
        try {
            sqLiteDatabase = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_LINK, favorite.getLink());
            values.put(KEY_VIEW, favorite.getCountView());
            values.put(KEY_LIKE, favorite.getCountLike());
            if (sqLiteDatabase.insert(TBNAME, null, values) != -1) {
                sqLiteDatabase.close();
                return true;
            }

        } catch (Exception e) {
            sqLiteDatabase.close();
            return false;
        }
        sqLiteDatabase.close();
        return false;
    }
    public boolean deleteImage(Favorite favorite) {
        sqLiteDatabase = this.getWritableDatabase();
        if (sqLiteDatabase.delete(TBNAME, KEY_ID + " = ? ", new String[]{String.valueOf(favorite.getId())}) > 0) {
            sqLiteDatabase.close();
            return true;
        }

        sqLiteDatabase.close();
        return false;
    }
    public List<Favorite> getAllImage() {
        List<Favorite> favoriteList = new ArrayList<>();
        SQLiteDatabase database = this.getReadableDatabase();
        try {
            String query = "SELECT * FROM " + TBNAME;
            Cursor cursor = database.rawQuery(query, null);
            cursor.moveToFirst();
            do {
                Favorite favorite = new Favorite();
                favorite.setId(cursor.getString(0));
                favorite.setLink(cursor.getString(1));
                favorite.setCountView(cursor.getString(2));
                favorite.setCountLike(cursor.getString(3));
                //add to list
                favoriteList.add(favorite);
            }
            while (cursor.moveToNext());
            database.close();
        } catch (Exception e) {
        }
        return favoriteList;

    }
}