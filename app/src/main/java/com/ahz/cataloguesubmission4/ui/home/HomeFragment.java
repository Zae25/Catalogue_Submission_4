package com.ahz.cataloguesubmission4.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ahz.cataloguesubmission4.R;
import com.ahz.cataloguesubmission4.adapter.MoviesAdapter;
import com.ahz.cataloguesubmission4.model.MoviesModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView rvMovies;
    private MoviesAdapter moviesAdapter;
    private ArrayList<MoviesModel> listItem = new ArrayList<>();
    private ProgressBar progressBar;

    public HomeFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        rvMovies = view.findViewById(R.id.rv_movies);
        progressBar = view.findViewById(R.id.progressBar);

        showRecyclerMovie();

        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(this.getContext()));
        moviesAdapter = new MoviesAdapter(new ArrayList<MoviesModel>());
        moviesAdapter.notifyDataSetChanged();
        rvMovies.setAdapter(moviesAdapter);

        homeViewModel.setMovieItem();
        showLoading(true);
    }

    private void showRecyclerMovie(){
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.getMovies().observe(this.getActivity(), getMovie);

        moviesAdapter = new MoviesAdapter(listItem);
        moviesAdapter.notifyDataSetChanged();

        homeViewModel.setMovieItem();
        showLoading(true);

        rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovies.setAdapter(moviesAdapter);
    }

    private Observer<ArrayList<MoviesModel>> getMovie = new Observer<ArrayList<MoviesModel>>() {
        @Override
        public void onChanged(ArrayList<MoviesModel> list) {
            showLoading(true);

            if (list != null);
            moviesAdapter.setData(list);
            showLoading(false);

        }
    };

    private void showLoading(Boolean state){
        if (state){
            progressBar.setVisibility(View.VISIBLE);
        } else{
            progressBar.setVisibility(View.GONE);
        }
    }
}
