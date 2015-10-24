package com.android.noflag.play.entity;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * description:
 * User: stridercheng
 * Date: 2015-10-06
 * Time: 14:29
 * FIXME
 */
public class Movie implements Serializable {
    private String movie_name, movie_director, movie_nation, movie_starring, movie_release_date, movie_description, movie_tags, movie_message, movie_type;
    private String movie_picture;
    private float movie_length, movie_score;
    private List<JSONObject> time_table;

    public Movie(String movie_name, String movie_director, String movie_nation, String movie_starring,
                 String movie_release_date, String movie_description, String movie_tags,
                 String movie_message, String movie_type, String movie_picture, float movie_length,
                 float movie_score, List<JSONObject> time_table) {
        this.movie_name = movie_name;
        this.movie_director = movie_director;
        this.movie_nation = movie_nation;
        this.movie_starring = movie_starring;
        this.movie_release_date = movie_release_date;
        this.movie_description = movie_description;
        this.movie_tags = movie_tags;
        this.movie_message = movie_message;
        this.movie_type = movie_type;
        this.movie_picture = movie_picture;
        this.movie_length = movie_length;
        this.movie_score = movie_score;
        this.time_table = time_table;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_director() {
        return movie_director;
    }

    public void setMovie_director(String movie_director) {
        this.movie_director = movie_director;
    }

    public String getMovie_nation() {
        return movie_nation;
    }

    public void setMovie_nation(String movie_nation) {
        this.movie_nation = movie_nation;
    }

    public String getMovie_starring() {
        return movie_starring;
    }

    public void setMovie_starring(String movie_starring) {
        this.movie_starring = movie_starring;
    }

    public String getMovie_release_date() {
        return movie_release_date;
    }

    public void setMovie_release_date(String movie_release_date) {
        this.movie_release_date = movie_release_date;
    }

    public String getMovie_description() {
        return movie_description;
    }

    public void setMovie_description(String movie_description) {
        this.movie_description = movie_description;
    }

    public String getMovie_tags() {
        return movie_tags;
    }

    public void setMovie_tags(String movie_tags) {
        this.movie_tags = movie_tags;
    }

    public String getMovie_message() {
        return movie_message;
    }

    public void setMovie_message(String movie_message) {
        this.movie_message = movie_message;
    }

    public String getMovie_type() {
        return movie_type;
    }

    public void setMovie_type(String movie_type) {
        this.movie_type = movie_type;
    }

    public String getMovie_picture() {
        return movie_picture;
    }

    public void setMovie_picture(String movie_picture) {
        this.movie_picture = movie_picture;
    }

    public float getMovie_length() {
        return movie_length;
    }

    public void setMovie_length(float movie_length) {
        this.movie_length = movie_length;
    }

    public float getMovie_score() {
        return movie_score;
    }

    public void setMovie_score(float movie_score) {
        this.movie_score = movie_score;
    }

    public List<JSONObject> getTime_table() {
        return time_table;
    }

    public void setTime_table(List<JSONObject> time_table) {
        this.time_table = time_table;
    }

    public Movie() {


    }
}
