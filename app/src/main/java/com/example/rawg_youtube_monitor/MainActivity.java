package com.example.rawg_youtube_monitor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.rawg_youtube_monitor.presentation.favGamesCollectionDisplay.fragment.FavGamesCollectionFragment;
import com.example.rawg_youtube_monitor.presentation.favGamesVideoDisplay.fragment.YoutubeVideoFragment;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.fragment.SearchGamesFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    //Contain bottom bar navigation
    private TabLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tableLayout = findViewById(R.id.tableLayout);
        setUpNavigationFragment();
        changeFragment(YoutubeVideoFragment.newInstance());
    }

    /**
     * Select the right fragment to put in
     * the screen by user touch
     */
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

    /**
     * replace current fragment
     * @param fragment
     */
    private void changeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentLayout,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
