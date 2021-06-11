package com.example.finalp.UI.Activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.finalp.Const;
import com.example.finalp.Data.Api.Repository.Callbacks.OnMovieCallback;
import com.example.finalp.Data.Api.Repository.MovieRepository;
import com.example.finalp.Data.Local.Services.FavoriteHelper;
import com.example.finalp.Data.Models.Movie;
import com.example.finalp.ImageSize;
import com.example.finalp.R;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class DetailActivity extends AppCompatActivity {

    private final String TAG = "MovieDetailsActivity";
    private int movieId;
    private Movie movie;
    private ImageView ivBackdrop;
    private ImageView ivPoster;
    private TextView tvTitle;
    private RatingBar rbRating;
    private TextView tvRuntime;
    private TextView tvReleaseYear;
    private TextView tvOverview;
    private TextView tvRatingLabel;
    private RecyclerView rvCredits;
    private TextView tvGenres;
    private RelativeLayout rlOverlay;
    private LinearLayout llCredits;
    private TextView tvError;
    private MovieRepository movieRepository;
    private FavoriteHelper favoriteHelper;
    private boolean isLoaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Loading");

        ivBackdrop = findViewById(R.id.iv_backdrop);
        ivPoster = findViewById(R.id.iv_poster);
        tvTitle = findViewById(R.id.tv_title);
        rbRating = findViewById(R.id.rb_rating);
        tvRatingLabel = findViewById(R.id.tv_rating_label);
        tvRuntime = findViewById(R.id.tv_runtime);
        tvReleaseYear = findViewById(R.id.tv_release_year);
        tvGenres = findViewById(R.id.tv_genres);
        tvOverview = findViewById(R.id.tv_overview);
        llCredits = findViewById(R.id.ll_credits);
        rvCredits = findViewById(R.id.rv_credits);
        rlOverlay = findViewById(R.id.rl_overlay);
        tvError = findViewById(R.id.tv_error);

        movieId = getIntent().getIntExtra(Const.MOVIE_ID, 0);
        movieRepository = MovieRepository.getRepository();
        favoriteHelper = new FavoriteHelper(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getRepositoryData(movieId);
    }

    private void getRepositoryData(int movieId) {
        movieRepository.getModelDetail(movieId, new OnMovieCallback() {
            @Override
            public void onFailure(String msg) {
                Log.d(TAG, "onFailure: "+msg);
            }

            @Override
            public void onSuccess(List<Movie> results, int page, String message) {
                movie = results;
                OnBindView(movie);
                isLoaded = true;
            }
        });
    }
    private void OnBindView(Movie movie){
        Objects.requireNonNull(getSupportActionBar()).setTitle(movie.getTitle());
        Glide.with(this)
                .load(movie.getBackdropPath(ImageSize.W1280))
                .into(ivBackdrop);

        Glide.with(this)
                .load(movie.getPosterPath(ImageSize.W342))
                .listener(new RequestListener<Drawable>() {

                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .error(R.drawable.ic_report_image)
                .into(ivPoster);

        tvTitle.setText(movie.getTitle());
        tvRatingLabel.setText(String.format(Locale.getDefault(), "%.1f", movie.getVoteAverage()));
        rbRating.setRating(movie.getVoteAverage());
        tvRuntime.setText(movie.getRuntime());
        tvOverview.setText(movie.getOverview());
        tvReleaseYear.setText(String.format(Locale.getDefault(), "(%s)", movie.getReleaseDate()));
        tvGenres.setText(movie.getGenres());

    }
}