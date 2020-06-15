package com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rawg_youtube_monitor.DependencyInjection;
import com.example.rawg_youtube_monitor.R;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.SearchGamesPresenter;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.SearchGamesViewContract;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.GameItemViewModel;
import com.example.rawg_youtube_monitor.presentation.searchGamesDisplay.adapter.SearchGameAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SearchGamesFragment extends Fragment implements SearchGamesViewContract {

    private View view;
    private RecyclerView searchGamesResultRecyclerView;
    private EditText searchGamesEditText;
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;

    private SearchGameAdapter searchGameAdapter;
    private SearchGamesPresenter searchGamesPresenter;


    private Timer timer = new Timer();
    private final long DELAY = 1000;

    public SearchGamesFragment() {
    }

    public static SearchGamesFragment newInstance() {
        return new SearchGamesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_search_games, container, false);
        this.searchGamesEditText = view.findViewById(R.id.searchGamesEditText);
        setUpSearchFieldListener();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        searchGamesPresenter = new SearchGamesPresenter(DependencyInjection.getGamesRepository());
        searchGamesPresenter.setSearchGamesViewContract(this);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        setUpRecyclerView();


    }

    public void setUpSearchFieldListener() {
        this.searchGamesEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                if (!s.toString().equals("")) {
                    timer.cancel();
                    timer = new Timer();
                    timer.schedule(
                            new TimerTask() {
                                @Override
                                public void run() {
                                    //Need UI thread to modify UI
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressBar.setVisibility(View.VISIBLE);
                                            searchGamesResultRecyclerView.setVisibility(View.GONE);
                                        }
                                    });
                                    searchGamesPresenter.getGamesByName(s.toString());
                                }
                            },
                            DELAY
                    );
                }

            }
        });

    }

    private void setUpRecyclerView() {
        this.searchGameAdapter = new SearchGameAdapter(this);
        this.searchGamesResultRecyclerView = view.findViewById(R.id.searchGamesResultRecyclerView);
        this.searchGamesResultRecyclerView.setVisibility(View.GONE);
        this.searchGamesResultRecyclerView.setAdapter(searchGameAdapter);
        linearLayoutManager = new LinearLayoutManager(view.getContext());
        this.searchGamesResultRecyclerView.setLayoutManager(linearLayoutManager);
        this.searchGamesResultRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { //check for scroll down
                    int visibleItemCount = linearLayoutManager.getChildCount();
                    int totalItemCount = linearLayoutManager.getItemCount();
                    int pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        progressBar.setVisibility(View.VISIBLE);
                        searchGamesPresenter.loadNextPage();
                    }
                }
            }
        });
    }


    @Override
    public void displayGames(List<GameItemViewModel> gameItemViewModelList,boolean newSearch) {
        this.searchGameAdapter.bindViewModels(gameItemViewModelList);
        progressBar.setVisibility(View.GONE);
        searchGamesResultRecyclerView.setVisibility(View.VISIBLE);
        if(newSearch)searchGamesResultRecyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void stopLoadingSpiner() {
        this.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void addGameToFavorite(String id) {
        this.searchGamesPresenter.addGameToFavorite(id);
    }

    @Override
    public void notifyGameAddedToFavorite(String message) {
        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void notifyErrorServeur(String message) {
        Snackbar.make(view,message,Snackbar.LENGTH_SHORT).show();
    }
}
