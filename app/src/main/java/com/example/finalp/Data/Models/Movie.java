package com.example.finalp.Data.Models;

import com.example.finalp.Const;
import com.example.finalp.ImageSize;
import com.google.gson.annotations.SerializedName;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

public class Movie {
    private int id, runtime;
    private String title;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("overview")
    private String overview;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("genres")
    private List<Genre> genres;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRuntime() {
        int hours = runtime/60;
        int minutes = runtime%60;
        String hourText;
        String minuteText;
        if (hours > 1) {
            hourText = "hours";
        } else {
            hourText = "hour";
        }
        if (minutes > 1) {
            minuteText = "minutes";
        } else {
            minuteText = "minute";
        }
        if (hours > 0) {
            return String.format(Locale.getDefault(), "%d %s %d %s", hours, hourText, minutes, minuteText);
        } else if (minutes > 0) {
            return String.format(Locale.getDefault(), "%d %s", minutes, minuteText);
        } else {
            return "";
        }
    }

    public String getBackdropPath(ImageSize size) {
        return Const.BASE_IMAGE_URL + size.getValue() + backdropPath;
    }

    public String getPosterPath(ImageSize size) {
        return Const.BASE_IMAGE_URL + size.getValue() + posterPath;
    }

    public String getReleaseDate() {
        return releaseDate.split("-")[0];
    }

    public String getOverview() {
        return overview;
    }

    public float getVoteAverage() {
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Float.parseFloat(df.format(voteAverage/2));
    }

    public String getGenres() {
        String output = "";
        for (int i = 0; i < genres.size(); i++) {
            output += genres.get(i).getName();
            if (i != genres.size() - 1) {
                output += ", ";
            }
        }
        return output;
    }
}
