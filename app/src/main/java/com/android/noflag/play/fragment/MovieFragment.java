package com.android.noflag.play.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.noflag.play.MyApplication;
import com.android.noflag.play.R;
import com.android.noflag.play.adapter.MovieAdapter;
import com.android.noflag.play.entity.Movie;
import com.android.noflag.play.utils.OkHttpClientManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MovieFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieFragment extends Fragment implements MovieAdapter.MovieAdapterItemClick {
    private final static String TAG = MovieFragment.class.getSimpleName();
    private MovieAdapter adapter;
    @InjectView(R.id.movielist)
    RecyclerView mList;
    @InjectView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MovieFragment.
     */
    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public void onResume() {
        super.onResume();
        new GetMovieAsynch().execute();
    }

    public void initData() {
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new GetMovieAsynch().execute();
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onMovieItemClick(View v, Movie movie) {
        Toast.makeText(getActivity(), "name" + movie.getMovie_name(), Toast.LENGTH_SHORT).show();
    }

    class GetMovieAsynch extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Void... params) {
            String result = "";
            OkHttpClientManager okHttpClientManager = OkHttpClientManager.getInstance();
            String url = MyApplication.movieUrl + "?wd=" + "新世纪电影城" + "&location=" + "济南" + "&rn=" + "15" + "&output=json&coord_type=bd09ll&out_coord_type=bd09ll";

            Request request = new Request.Builder().url(url).get().header("apikey", MyApplication.movieKey).build();
            try {
                Response response = okHttpClientManager.getGetDelegate().get(request);
                result = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String jsonArray) {
            List<Movie> movieList;
            try {
                JSONObject cinema = new JSONObject(jsonArray);
                JSONArray movies = ((JSONObject)cinema.getJSONArray("result").get(0)).getJSONArray("movies");
                Gson gson = new Gson();
                movieList = gson.fromJson(movies.toString(), new TypeToken<List<Movie>>() {
                }.getType());
                if (adapter != null) {
                    adapter.setMovieList(movieList);
                    adapter.notifyDataSetChanged();
                } else {
                    adapter = new MovieAdapter(getActivity(), movieList);
                    adapter.setMovieAdapterItemClick(MovieFragment.this);
                    mList.setAdapter(adapter);
                }
            } catch (JSONException e) {
                Toast.makeText(getActivity(), "请求数据出错", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            refreshLayout.setRefreshing(false);
            super.onPostExecute(jsonArray);
        }
    }
}
