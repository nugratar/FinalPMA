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
import com.example.finalp.Data.Models.Favorite;
import com.example.finalp.ImageSize;
import com.example.finalp.R;
import com.example.finalp.UI.Adapters.ClickListeners.OnFavoriteClickListener;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    private final List<Favorite> modelFavoriteList;
    private final OnFavoriteClickListener onFavoriteClickListener;
    private static FavoriteAdapter favoriteAdapter;

    public FavoriteAdapter(List<Favorite> modelFavoriteList, OnFavoriteClickListener onFavoriteClickListener){
        this.modelFavoriteList = modelFavoriteList;
        this.onFavoriteClickListener = onFavoriteClickListener;
    }

    public static FavoriteAdapter getInstance() {
        if (favoriteAdapter == null) {
            throw new IllegalStateException("instance null, forgot to initialize?");
        }
        return favoriteAdapter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(modelFavoriteList.get(position).getPosterPath(ImageSize.W200))
                .placeholder(new ColorDrawable(Color.BLACK))
                .error(R.drawable.ic_report_image)
                .into(holder.ivPoster);
        holder.tvTitle.setText(modelFavoriteList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return modelFavoriteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView ivPoster;
        private final TextView tvTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }

        @Override
        public void onClick(View v) {
            onFavoriteClickListener.onClick(modelFavoriteList.get(getBindingAdapterPosition()));
        }
    }
}
