package com.example.rawg_youtube_monitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TableLayout;

import com.example.rawg_youtube_monitor.presentation.MainPageAdapter;
import com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.fragment.FavGamesCollectionFragment;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.fragment.YoutubeVideoFragment;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.fragment.SearchGamesFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private TabLayout tableLayout;
    private FrameLayout fragmentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = findViewById(R.id.tableLayout);
        fragmentLayout = findViewById(R.id.fragmentLayout);
        setUpTabItemIcons();
        setUpNavigationFragment();
        changeFragment(YoutubeVideoFragment.newInstance());
    }



    private void setUpTabItemIcons(){
        int nbTabs = tableLayout.getTabCount();
        if(nbTabs==3){
            tableLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_subscriptions_24);
            tableLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_apps_24);
            tableLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_search_24);
        }
    }
    private void setUpNavigationFragment() {
        tableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0: changeFragment(YoutubeVideoFragment.newInstance());break;
                    case 1: changeFragment(FavGamesCollectionFragment.newInstance());break;
                    case 2: changeFragment(SearchGamesFragment.newInstance());break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}

        });
    }

    private void changeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
