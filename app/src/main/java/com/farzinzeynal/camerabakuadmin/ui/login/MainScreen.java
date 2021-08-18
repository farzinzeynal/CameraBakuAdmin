package com.farzinzeynal.camerabakuadmin.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.farzinzeynal.camerabakuadmin.R;
import com.google.android.material.tabs.TabLayout;

public class MainScreen extends AppCompatActivity {

    public TabLayout tabs;
    public ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this,getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);

        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }
}