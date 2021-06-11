package com.example.finalp.UI.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finalp.R;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.ViewHolder> {

    private final List<String> listGgenre;
    private final Context context;

    public GenreAdapter(List<String> genre, Context context) {
        this.listGgenre = genre;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_genre, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final String genre = listGgenre.get(position);
        holder.setTvGenre(genre);
    }

    @Override
    public int getItemCount() {
        return listGgenre == null ? 0 : listGgenre.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvGenre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvGenre = itemView.findViewById(R.id.tv_genre);
        }

        public void setTvGenre(String genre){
            tvGenre.setText(genre);
        }
    }
}
