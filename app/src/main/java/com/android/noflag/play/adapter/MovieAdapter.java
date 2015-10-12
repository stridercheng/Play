package com.android.noflag.play.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.noflag.play.R;
import com.android.noflag.play.entity.Movie;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * description:
 * User: stridercheng
 * Date: 2015-10-06
 * Time: 14:26
 * FIXME
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> implements View.OnClickListener{
    private final static String TAG = MovieAdapter.class.getSimpleName();
    private ImageLoader imageLoader;
    private List<Movie> movieList;
    private LayoutInflater inflater;
    private MovieAdapterItemClick movieAdapterItemClick;

    public MovieAdapter(Context context, List<Movie> movieList) {
        inflater = LayoutInflater.from(context);
        imageLoader = ImageLoader.getInstance();
        this.movieList = movieList;
    }

    public void setMovieAdapterItemClick(MovieAdapterItemClick movieAdapterItemClick) {
        this.movieAdapterItemClick = movieAdapterItemClick;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.movie_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Movie movie = movieList.get(i);
        viewHolder.name.setText(movie.getMovie_name());
        viewHolder.director.setText(movie.getMovie_director());
        viewHolder.star.setText(movie.getMovie_starring());
        viewHolder.time.setText(movie.getMovie_release_date());
        imageLoader.displayImage(movie.getMovie_picture(), viewHolder.moviepic);

        viewHolder.itemView.setTag(movieList.get(i));
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    @Override
    public void onClick(View v) {
        if (movieAdapterItemClick != null) {
            movieAdapterItemClick.onMovieItemClick(v, (Movie) v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, director, star, time;
        ImageView moviepic;
        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = (TextView) itemView.findViewById(R.id.moviename);
            director = (TextView) itemView.findViewById(R.id.director);
            star = (TextView) itemView.findViewById(R.id.stars);
            time = (TextView) itemView.findViewById(R.id.time);
            moviepic = (ImageView) itemView.findViewById(R.id.moviepic);
        }
    }

    public interface MovieAdapterItemClick{
        void onMovieItemClick(View v, Movie movie);
    }
}
