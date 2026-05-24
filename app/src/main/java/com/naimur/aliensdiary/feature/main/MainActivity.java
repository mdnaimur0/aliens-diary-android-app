package com.naimur.aliensdiary.feature.main;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.view.View;

import com.google.android.material.tabs.TabLayoutMediator;
import com.naimur.aliensdiary.R;
import com.naimur.aliensdiary.feature.audio.AudioFragment;
import com.naimur.aliensdiary.feature.photo.PhotoFragment;
import com.naimur.aliensdiary.feature.video.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        List<String> titleList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();

        titleList.add("Photos");
        fragmentList.add(new PhotoFragment());

        titleList.add("Videos");
        fragmentList.add(new VideoFragment());

        titleList.add("Audios");
        fragmentList.add(new AudioFragment());

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(fragmentList, getSupportFragmentManager(), getLifecycle());
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        TabLayout tabs = findViewById(R.id.tabs);
        new TabLayoutMediator(tabs, viewPager,
                (tab, position) -> tab.setText(titleList.get(position))).attach();

        FloatingActionButton fab = findViewById(R.id.fabAdd);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }
}