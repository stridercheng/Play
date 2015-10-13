package com.android.noflag.play;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.LinearLayout;

import com.android.noflag.play.adapter.MainViewPagerAdapter;
import com.android.noflag.play.fragment.MovieFragment;
import com.android.noflag.play.fragment.SceneFragment;
import com.android.noflag.play.fragment.TravelNoteFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragmentList;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabs)
    TabLayout tabLayout;
    @InjectView(R.id.pagers)
    ViewPager pagers;
    @InjectView(R.id.head)
    AppBarLayout ll_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(toolbar);

        initData();
    }

    private void initData() {
        fragmentList = new ArrayList<>();
        MovieFragment movieFragment = MovieFragment.newInstance();
        SceneFragment sceneFragment = SceneFragment.newInstance();
        TravelNoteFragment travelNoteFragment = TravelNoteFragment.newInstance("", "");
        fragmentList.add(movieFragment);
        fragmentList.add(sceneFragment);
        fragmentList.add(travelNoteFragment);

        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager(), fragmentList);
        pagers.setAdapter(adapter);
        pagers.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(pagers);

        pagers.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        ll_head.setBackgroundResource(R.mipmap.head_movie);
                        break;
                    case 1:
                        ll_head.setBackgroundResource(R.mipmap.head_scene);
                        break;
                    case 2:
                        ll_head.setBackgroundResource(R.mipmap.head_note);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie, menu);
        return true;
    }
}
