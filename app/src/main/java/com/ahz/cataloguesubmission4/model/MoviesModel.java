package com.ahz.cataloguesubmission4.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONObject;

public class MoviesModel implements Parcelable {
    private String name;
    private String date;
    private String description;
    private String poster;

    public MoviesModel(JSONObject object){
        try {
            String name = object.getString("title");
            String date = object.getString("release_date");
            String description = object.getString("overview");
            String poster = object.getString("poster_path");

            this.name = name;
            this.date = date;
            this.description = description;
            this.poster = poster;
        } catch (Exception e){
            e.printStackTrace();
            Log.d("Error Data", e.getMessage());
        }
    }

    private MoviesModel(Parcel in) {
        name = in.readString();
        date = in.readString();
        description = in.readString();
        poster = in.readString();
    }

    public static final Creator<MoviesModel> CREATOR = new Creator<MoviesModel>() {
        @Override
        public MoviesModel createFromParcel(Parcel in) {
            return new MoviesModel(in);
        }

        @Override
        public MoviesModel[] newArray(int size) {
            return new MoviesModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(date);
        parcel.writeString(description);
        parcel.writeString(poster);
    }

    public MoviesModel(){}
}
