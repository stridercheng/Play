package com.android.noflag.play.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.noflag.play.R;
import com.android.noflag.play.db.DBManager;
import com.android.noflag.play.entity.Movie;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.w3c.dom.Text;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * description:
 * User: stridercheng
 * Date: 2015-10-12
 * Time: 21:59
 * FIXME
 */
public class MovieActivity extends AppCompatActivity {
    private Movie movie;
    @InjectView(R.id.moviename)
    TextView tv_movieName;
    @InjectView(R.id.director)
    TextView tv_movie_director;
    @InjectView(R.id.stars)
    TextView tv_stars;
    @InjectView(R.id.time)
    TextView tv_time;
    @InjectView(R.id.nation)
    TextView tv_nation;
    @InjectView(R.id.tags)
    TextView tv_tags;
    @InjectView(R.id.tv_type)
    TextView tv_type;
    @InjectView(R.id.length)
    TextView tv_length;
    @InjectView(R.id.score)
    TextView tv_score;
    @InjectView(R.id.description)
    TextView tv_description;
    @InjectView(R.id.moviepic)
    ImageView iv_moviePic;

    ImageLoader imageLoader;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.inject(this);
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        DBManager dbManager = new DBManager(this);
        movie = dbManager.queryMovie(name);
        if (movie == null) {
            Toast.makeText(MovieActivity.this, "影片不存在，请刷新", Toast.LENGTH_SHORT).show();
            return;
        }
        initData();
    }

    private void initData() {
        imageLoader = ImageLoader.getInstance();
        tv_movieName.setText(movie.getMovie_name());
        tv_movie_director.setText(movie.getMovie_director());
        tv_stars.setText(movie.getMovie_starring());
        tv_time.setText(movie.getMovie_release_date());
        tv_nation.setText(movie.getMovie_nation());
        tv_tags.setText(movie.getMovie_tags());
        tv_length.setText(String.valueOf(movie.getMovie_length()));
        tv_type.setText(movie.getMovie_type().trim());
        tv_score.setText(String.valueOf(movie.getMovie_score()));
        tv_description.setText(movie.getMovie_description());
        imageLoader.displayImage(movie.getMovie_picture(), iv_moviePic);
    }
}
