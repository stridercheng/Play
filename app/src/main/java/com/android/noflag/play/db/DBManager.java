package com.android.noflag.play.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.noflag.play.entity.Movie;

/**
 * Created by Administrator on 2015/10/24.
 */
public class DBManager {
    private DBHelper dbHelper;
    public DBManager(Context context) {
        dbHelper = DBHelper.getInstance(context);
    }

    public void saveMovie(Movie movie) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String insert = "insert into movie(movie_name, movie_director, movie_nation, movie_staring," +
                "movie_release_date, movie_description, movie_tags, movie_message, movie_type, movie_picture," +
                "movie_length, movie_score, time_table) values ('" + movie.getMovie_name() + "','" + movie.getMovie_director() + "','"
                + movie.getMovie_nation() + "','" + movie.getMovie_starring() + "','" + movie.getMovie_release_date() + "','" + movie.getMovie_description()
                +"','" + movie.getMovie_tags() + "','" + movie.getMovie_message() + "','" + movie.getMovie_type() + "','" + movie.getMovie_picture()
                +"','" + movie.getMovie_length() + "','" + movie.getMovie_score() + "','" + movie.getTime_table() +"')" ;

        db.execSQL(insert);
        db.close();
    }

    public Movie queryMovie(String name) {
        Movie movie = null;
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String query = "select * from movie where movie_name ='" + name + "'";
        Cursor cursor = db.rawQuery(query, new String[]{});
        if (cursor.moveToFirst()) {
            movie = new Movie();
            movie.setMovie_name(cursor.getString(cursor.getColumnIndex("movie_name")));
            movie.setMovie_director(cursor.getString(cursor.getColumnIndex("movie_director")));
            movie.setMovie_nation(cursor.getString(cursor.getColumnIndex("movie_nation")));
            movie.setMovie_starring(cursor.getString(cursor.getColumnIndex("movie_staring")));
            movie.setMovie_release_date(cursor.getString(cursor.getColumnIndex("movie_release_date")));
            movie.setMovie_description(cursor.getString(cursor.getColumnIndex("movie_description")));
            movie.setMovie_tags(cursor.getString(cursor.getColumnIndex("movie_tags")));
            movie.setMovie_message(cursor.getString(cursor.getColumnIndex("movie_message")));
            movie.setMovie_type(cursor.getString(cursor.getColumnIndex("movie_type")));
            movie.setMovie_picture(cursor.getString(cursor.getColumnIndex("movie_picture")));
            movie.setMovie_length(cursor.getFloat(cursor.getColumnIndex("movie_length")));
            movie.setMovie_score(cursor.getFloat(cursor.getColumnIndex("movie_score")));
        }
        return movie;
    }
}
