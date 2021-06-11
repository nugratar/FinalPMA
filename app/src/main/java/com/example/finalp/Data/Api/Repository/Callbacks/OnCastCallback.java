package com.example.finalp.Data.Api.Repository.Callbacks;

import com.example.finalp.Data.Models.Cast;

import java.util.List;

public interface OnCastCallback {
    void onSuccess(List<Cast> castList, String message);
    void onFailure(String message);
}
