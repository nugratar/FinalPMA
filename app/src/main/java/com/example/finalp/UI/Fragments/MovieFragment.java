package com.example.finalp.UI.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.finalp.Const;
import com.example.finalp.Data.Api.Repository.Callbacks.OnMovieListCallback;
import com.example.finalp.Data.Api.Repository.Callbacks.OnSearchCallback;
import com.example.finalp.Data.Api.Repository.MovieRepository;
import com.example.finalp.Data.Models.ListMovie;
import com.example.finalp.Data.Models.Movie;
import com.example.finalp.R;
import com.example.finalp.UI.Activities.DetailActivity;
import com.example.finalp.UI.Adapters.ClickListeners.OnItemClickListener;
import com.example.finalp.UI.Adapters.MovieAdapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayoutManager;

import java.util.List;

import static android.content.ContentValues.TAG;

public class MovieFragment extends Fragment implements OnItemClickListener<ListMovie>, SwipeRefreshLayout.OnRefreshListener, SearchView.OnQueryTextListener {

    private RecyclerView recyclerView;
    private SearchView searchView;
    private SwipeRefreshLayout refreshLayout;
    private TextView tvError;
    private MovieAdapter movieAdapter;
    private MovieRepository movieRepository;
    private String query = "";
    private boolean isFetching;
    private int currentPage = 1;

    public MovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie, container, false);

        refreshLayout = view.findViewById(R.id.swl_movie);
        recyclerView = view.findViewById(R.id.rv_movie);
        tvError = view.findViewById(R.id.tv_error);

        movieRepository = MovieRepository.getRepository();
        onScrollListener();
//        getRepositoryData(currentPage);
        refreshLayout.setOnRefreshListener(this);

        return view;
    }

    private void onScrollListener() {
        FlexboxLayoutManager flexboxLayoutManager = new FlexboxLayoutManager(getContext());
        flexboxLayoutManager.setAlignItems(AlignItems.FLEX_START);
        flexboxLayoutManager.setFlexDirection(FlexDirection.ROW);
        flexboxLayoutManager.setFlexWrap(FlexWrap.WRAP);
        
        recyclerView.setLayoutManager(flexboxLayoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                
                int movieItems = flexboxLayoutManager.getItemCount();
                int visibleItems = flexboxLayoutManager.getChildCount();
                int firstVisibleItem = flexboxLayoutManager.findFirstVisibleItemPosition();
                
                if (firstVisibleItem + visibleItems >= movieItems / 2) {
                    if (!isFetching) {
                        getRepositoryData(currentPage + 1, null);
                    }
                }
            }
        });
    }

    private void getRepositoryData(int page, Object o) {
        isFetching = true;
        if (query.isEmpty()) {
            movieRepository.getModel(getBundle(), page, new OnMovieListCallback() {
                @Override
                public void onSuccess(List<ListMovie> results, int page, String message) {
                    if (movieAdapter == null) {
                        movieAdapter = new MovieAdapter(results);
                        movieAdapter.setClickListener(MovieFragment.this);
                        movieAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(movieAdapter);
                    } else {
                        movieAdapter.appendList(results);
                    }
                    currentPage = page;
                    isFetching = false;
                    refreshLayout.setRefreshing(false);
                }

                @Override
                public void onSuccess(List<ListMovie> movieList, int page, String message) {
                    if (movieAdapter == null) {
                        movieAdapter = new MovieAdapter(movieList);
                        movieAdapter.setClickListener(MovieFragment.this);
                        movieAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(movieAdapter);
                    } else {
                        movieAdapter.appendList(movieList);
                    }
                    currentPage = page;
                    isFetching = false;
                    refreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(String msg) {
                    Log.d(TAG, "onFailure: " + msg);
                    refreshLayout.setRefreshing(false);
                    recyclerView.setAdapter(null);
                }
            });
        } else {
            movieRepository.search(query, page, new OnSearchCallback() {
                @Override
                public void onSuccess(List<ListMovie> results, int page, String message) {
                    if (movieAdapter == null) {
                        movieAdapter = new MovieAdapter(results);
                        movieAdapter.setClickListener(MovieFragment.this);
                        movieAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(movieAdapter);
                    } else {
                        movieAdapter.appendList(results);
                    }
                    currentPage = page;
                    isFetching = false;
                    refreshLayout.setRefreshing(false);
                }

                @Override
                public void onFailure(String message) {
                    Log.d(TAG, "onFailure: " + message);
                    refreshLayout.setRefreshing(false);
                    recyclerView.setAdapter(null);
                }
            });
        }
    }

    private String getBundle() {
        if (getArguments() != null) {
            return getArguments().getString("QUERY_TYPE");
        }
        return "now_playing";
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        query = newText;
        movieAdapter = null;
        currentPage = 1;
        getRepositoryData(currentPage, null);
        return true;
    }

    @Override
    public void onRefresh() {
        movieAdapter = null;
        currentPage = 1;
        getRepositoryData(currentPage, null);
    }

    @Override
    public void onClick(ListMovie listMovie) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(Const.MOVIE_ID, listMovie.getId());
        startActivity(intent);
    }
}