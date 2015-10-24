package com.android.noflag.play.fragment;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.noflag.play.MyApplication;
import com.android.noflag.play.R;
import com.android.noflag.play.adapter.MovieAdapter;
import com.android.noflag.play.adapter.SceneAdapter;
import com.android.noflag.play.entity.Movie;
import com.android.noflag.play.entity.Scene;
import com.android.noflag.play.utils.FastBlur;
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
 * {@link SceneFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SceneFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SceneFragment extends Fragment {
    SceneAdapter adapter;
    @InjectView(R.id.scenelist)
    RecyclerView sceneList;
    @InjectView(R.id.refresh)
    SwipeRefreshLayout refreshLayout;
    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SceneFragment.
     */
    public static SceneFragment newInstance() {
        SceneFragment fragment = new SceneFragment();
        return fragment;
    }

    public SceneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scene, container, false);
        ButterKnife.inject(this, view);

//        applyBlur();
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    class GetSceneAsynch extends AsyncTask<Void, Void, String> {

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
            List<Scene> sceneList;
            try {
                JSONObject cinema = new JSONObject(jsonArray);
                JSONArray movies = ((JSONObject) cinema.getJSONArray("result").get(0)).getJSONArray("movies");
                Gson gson = new Gson();
                sceneList = gson.fromJson(movies.toString(), new TypeToken<List<Movie>>() {
                }.getType());

                adapter = new SceneAdapter(getActivity(), sceneList);
                SceneFragment.this.sceneList.setAdapter(adapter);
//                saveMovieToDB(sceneList);
            } catch (JSONException e) {
                Toast.makeText(getActivity(), "请求数据出错", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            refreshLayout.setRefreshing(false);
            super.onPostExecute(jsonArray);
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onFragmentInteraction(Uri uri);
    }

}
