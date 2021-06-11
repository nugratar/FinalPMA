package com.example.finalp.UI.Adapters;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.finalp.Data.Local.Models.FavoriteMovie;
import com.example.finalp.Data.Models.ListMovie;
import com.example.finalp.ImageSize;
import com.example.finalp.R;
import com.example.finalp.UI.Adapters.ClickListeners.OnItemClickListener;

import java.util.List;

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.ViewHolder> {

    private final List<FavoriteMovie> listMovies;
    private final OnItemClickListener<ListMovie> clickListener;

    public FavoriteMovieAdapter(List<FavoriteMovie> listMovies, OnItemClickListener<ListMovie> clickListener) {
        this.listMovies = listMovies;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load(listMovies.get(position).getPosterPath(ImageSize.W200))
                .placeholder(new ColorDrawable(Color.BLACK))
                .error(R.drawable.ic_report_image)
                .into(holder.ivPoster);
        holder.tvTitle.setText(listMovies.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return listMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        FavoriteMovie favoriteMovie;
        private final ImageView ivPoster;
        private final TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener((View.OnClickListener) this);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
