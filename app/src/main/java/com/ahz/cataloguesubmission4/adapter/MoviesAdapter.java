package com.ahz.cataloguesubmission4.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahz.cataloguesubmission4.R;
import com.ahz.cataloguesubmission4.model.MoviesModel;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ListViewHolder> {
    private ArrayList<MoviesModel> listMovie = new ArrayList<>();

    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public MoviesAdapter (ArrayList<MoviesModel> list){
        this.listMovie = list;
    }

    public void setData (ArrayList<MoviesModel> items){
        listMovie.clear();
        listMovie.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_film, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bind(listMovie.get(position));

    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        final ImageView imgPoster;
        final TextView tvName, tvDate,tvDesc;

        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPoster = itemView.findViewById(R.id.tv_item_poster);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvDesc = itemView.findViewById(R.id.tv_item_description);
        }
        void bind(final MoviesModel moviesModel){
            String url_image = "https://image.tmdb.org/t/p/w185/" + moviesModel.getPoster();

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .into(imgPoster);

            tvName.setText(moviesModel.getName());
            tvDate.setText(moviesModel.getDate());
            tvDesc.setText(moviesModel.getDescription());
        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(MoviesModel dataMovie);
    }
}
