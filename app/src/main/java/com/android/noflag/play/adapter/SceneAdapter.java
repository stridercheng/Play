package com.android.noflag.play.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.noflag.play.R;
import com.android.noflag.play.entity.Scene;

import java.util.List;

/**
 * Created by Administrator on 2015/10/24.
 */
public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.ViewHolder> {
    private List<Scene> sceneList;
    private LayoutInflater inflater;
    public SceneAdapter(Context context, List<Scene> sceneList) {
        inflater = LayoutInflater.from(context);
        this.sceneList = sceneList;
    }

    @Override
    public SceneAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.scene_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SceneAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return sceneList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
