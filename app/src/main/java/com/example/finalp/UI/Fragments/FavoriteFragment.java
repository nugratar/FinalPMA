package com.example.finalp.UI.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalp.R;
import com.example.finalp.UI.Adapters.FavoriteAdapter;

public class FavoriteFragment extends Fragment {
    private RecyclerView recyclerView;

    public FavoriteFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, viewGroup, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FavoriteAdapter favoriteAdapter = FavoriteAdapter.getInstance();

        recyclerView = view.findViewById(R.id.rv_favorite);
        recyclerView.setAdapter(favoriteAdapter);

        if (favoriteAdapter.getItemCount() > 0) {
            view.<TextView>findViewById(R.id.tv_error).setVisibility(View.GONE);
        }
    }

}