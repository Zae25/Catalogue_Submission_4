package com.ahz.cataloguesubmission4.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahz.cataloguesubmission4.model.MoviesModel;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class HomeViewModel extends ViewModel {

    private static final String API_KEY = "afec46e7feb0bd71f410b4eaf0e3892a";
    private MutableLiveData<ArrayList<MoviesModel>>listMovieItem = new MutableLiveData<>();

    public void setMovieItem(){
        // request API

        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<MoviesModel> items = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US";

        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++){
                        JSONObject movies = list.getJSONObject(i);
                        MoviesModel movieItem = new MoviesModel(movies);
                        items.add(movieItem);
                    }
                    listMovieItem.postValue(items);
                } catch (JSONException e){
                    Log.d("onFailure", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<MoviesModel>> getMovies(){
        return listMovieItem;
    }
}