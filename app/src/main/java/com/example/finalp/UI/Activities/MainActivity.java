package com.example.finalp.UI.Activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.finalp.R;
import com.example.finalp.UI.Fragments.FavoriteFragment;
import com.example.finalp.UI.Fragments.MovieFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    FrameLayout flMain;
    BottomNavigationView bnvMain;
    Fragment fragment;
    String query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flMain = findViewById(R.id.frame_layout);
        bnvMain = findViewById(R.id.bottom_navigation);
        bnvMain.setOnNavigationItemSelectedListener(this);
        bnvMain.setSelectedItemId(R.id.item_now_playing);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        fragment = null;
        query = null;

        int id = item.getItemId();

        if (id == R.id.item_now_playing){
            getSupportActionBar().setTitle("Now Playing");
            fragment = new MovieFragment();
        } else if (id == R.id.item_upcoming) {
            getSupportActionBar().setTitle("Upcoming");
            fragment = new MovieFragment();
            query = "upcoming";
        } else if (id == R.id.item_popular) {
            getSupportActionBar().setTitle("Popular");
            fragment = new MovieFragment();
            query = "popular";
        } else if (id == R.id.item_favorite) {
            getSupportActionBar().setTitle("Favorite");
            fragment = new FavoriteFragment();
            query = "favourites";
        }

        if (fragment != null) {
            getSupportActionBar().setTitle("Empty");
            return true;
        } else {
            return false;
        }
    }
}