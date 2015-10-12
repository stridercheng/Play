package com.android.noflag.play.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.noflag.play.R;
import com.android.noflag.play.entity.Movie;
import com.android.noflag.play.utils.LogUtil;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * description:
 * User: stridercheng
 * Date: 2015-10-06
 * Time: 14:26
 * FIXME
 */
public class MovieAdapter extends BaseAdapter {
    private final static String TAG = MovieAdapter.class.getSimpleName();
    private ImageLoader imageLoader;
    private List<Movie> movieList;
    private LayoutInflater inflater;

    public MovieAdapter(Context context, List<Movie> movieList) {
        inflater = LayoutInflater.from(context);
        imageLoader = ImageLoader.getInstance();
        this.movieList = movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList.clear();
        this.movieList = movieList;
    }

    @Override
    public int getCount() {
        return movieList.size();
    }

    @Override
    public Object getItem(int position) {
        return movieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.movie_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Movie movie = movieList.get(position);

        viewHolder.name.setText(movie.getMovie_name());
        viewHolder.director.setText(movie.getMovie_director());
        viewHolder.star.setText(movie.getMovie_starring());
        viewHolder.time.setText(movie.getMovie_release_date());
        imageLoader.displayImage(movie.getMovie_picture(), viewHolder.moviepic);
        LogUtil.e(TAG, viewHolder.moviepic.getWidth() + ";" + viewHolder.moviepic.getHeight());
        return convertView;
    }

    class ViewHolder {
        TextView name, director, star, time;
        ImageView moviepic;
        public ViewHolder(View view) {
            name = (TextView) view.findViewById(R.id.moviename);
            director = (TextView) view.findViewById(R.id.director);
            star = (TextView) view.findViewById(R.id.stars);
            time = (TextView) view.findViewById(R.id.time);
            moviepic = (ImageView) view.findViewById(R.id.moviepic);
        }
    }
}
