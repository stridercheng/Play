package com.android.noflag.play.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.noflag.play.MyApplication;

/**
 * Created by Administrator on 2015/10/24.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static DBHelper dbHelper = null;
    public static synchronized DBHelper getInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }

        return dbHelper;
    }

    private DBHelper(Context context) {
        super(context, MyApplication.appPath + "play.db3", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String movieSql = "create table if not exists movie(movie_name text, movie_director text, movie_nation text, movie_staring text,"
                + "movie_release_date text, movie_description text, movie_tags text, movie_message text, movie_type text, movie_picture text,"
                + "movie_length real, movie_score real, time_table text)";
        db.execSQL(movieSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
