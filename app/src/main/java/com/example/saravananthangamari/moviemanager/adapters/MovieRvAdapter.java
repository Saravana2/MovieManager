package com.example.saravananthangamari.moviemanager.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saravananthangamari.moviemanager.R;
import com.example.saravananthangamari.moviemanager.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieRvAdapter extends RecyclerView.Adapter<MovieRvAdapter.ViewHolder> {
List<Movie> movies;
Context context;
    public MovieRvAdapter(Context context, List<Movie> movies){
    this.movies=movies;
    this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(v);
    }

    private Context getContext(){
        return context;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Movie movie=movies.get(position);
    holder.tv_now_title.setText(movie.getMovieTitle());
    holder.tv_now_overview.setText(movie.getMovieOverview());
    Picasso.with(getContext()).load(movie.getPosterPath())
                .into(holder.imageView_now);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

     public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageView_now)
        ImageView imageView_now;
        @BindView(R.id.tv_now_title)
        TextView tv_now_title;
        @BindView(R.id.tv_now_overview)
        TextView tv_now_overview;
        @BindView(R.id.now_cv)
        CardView now_cv;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
