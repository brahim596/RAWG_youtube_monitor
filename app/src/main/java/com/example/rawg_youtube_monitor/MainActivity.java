package com.example.rawg_youtube_monitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.rawg_youtube_monitor.presentation.MainPageAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPage);
        tableLayout = findViewById(R.id.tableLayout);
        tableLayout.setupWithViewPager(viewPager);
        viewPager.setAdapter(new MainPageAdapter(getSupportFragmentManager()));

        setUpTabItemIcons();
    }


    private void setUpTabItemIcons(){
        int nbTabs = tableLayout.getTabCount();

        if(nbTabs==3){
            tableLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_subscriptions_24);
            tableLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_apps_24);
            tableLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_search_24);

        }
    }
}