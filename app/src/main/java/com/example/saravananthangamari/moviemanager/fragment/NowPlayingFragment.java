package com.example.saravananthangamari.moviemanager.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.saravananthangamari.moviemanager.R;
import com.example.saravananthangamari.moviemanager.adapters.MovieRvAdapter;
import com.example.saravananthangamari.moviemanager.models.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NowPlayingFragment extends Fragment {
    @BindView(R.id.now_rvmovie)
    RecyclerView now_rvmovie;

    private List<Movie> movies;

    public NowPlayingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        ButterKnife.bind(this,view);
        initilaizeData();
        LinearLayoutManager llm=new LinearLayoutManager(this.getContext());
        now_rvmovie.setHasFixedSize(true);
        now_rvmovie.setLayoutManager(llm);

        MovieRvAdapter movieRvAdapter=new MovieRvAdapter(getContext(),movies);
        now_rvmovie.setAdapter(movieRvAdapter);
        return view;

    }

    private void initilaizeData() {
        movies = new ArrayList<>();
        movies.add(new Movie("277834", "Moana", "In Ancient Polynesia, when a terrible curse incurred by Maui reaches an impetuous Chieftain's daughter's island, she answers the Ocean's call to seek out the demigod to set things right.", 6.5f, 854, "https://upload.wikimedia.org/wikipedia/en/thumb/2/26/Moana_Teaser_Poster.jpg/220px-Moana_Teaser_Poster.jpg", "http://cdn3-www.comingsoon.net/assets/uploads/2017/01/moanasing.jpg"));
        movies.add(new Movie("121856", "Passengers", "A spacecraft traveling to a distant colony planet and transporting thousands of people has a malfunction in its sleep chambers. As a result, two passengers are awakened 90 years early.", 6.2f, 745, "http://www.sonypictures.com/movies/passengers/assets/images/onesheet.jpg", "http://jpcawood.com/wp-content/uploads/2016/12/passengers-movie.jpg"));
        movies.add(new Movie("330459", "Assassin's Creed", "Lynch discovers he is a descendant of the secret Assassins society through unlocked genetic memories that allow him to relive the adventures of his ancestor, Aguilar, in 15th Century Spain. After gaining incredible knowledge and skills he’s poised to take on the oppressive Knights Templar in the present day.", 5.3f, 691, "https://upload.wikimedia.org/wikipedia/en/thumb/7/77/Assassins_Creed_2_Box_Art.JPG/220px-Assassins_Creed_2_Box_Art.JPG", "http://img.reblog.hu/blogs/24736/acm-ubi-thumb-leap-mobile-275895458d.jpg"));
        movies.add(new Movie("283366", "Rogue One: A Star Wars Story", "A rogue band of resistance fighters unite for a mission to steal the Death Star plans and bring a new hope to the galaxy.", 7.2f, 1802, "https://images-na.ssl-images-amazon.com/images/I/61cPoerQkNL._SX342_.jpg", "http://www.jornaldomonotrilho.com.br/wp-content/uploads/2016/12/1440x810.jpg"));
        movies.add(new Movie("313369", "La La Land", "Mia, an aspiring actress, serves lattes to movie stars in between auditions and Sebastian, a jazz musician, scrapes by playing cocktail party gigs in dingy bars, but as success mounts they are faced with decisions that begin to fray the fragile fabric of their love affair, and the dreams they worked so hard to maintain in each other threaten to rip them apart.", 8, 396, "https://is4-ssl.mzstatic.com/image/thumb/Video111/v4/08/8b/bd/088bbd0c-cf61-cf78-2573-3d43609ca2a4/source/227x227bb.jpg", "http://getthechance.wales/wp-content/uploads/2017/01/la-la-land-poster.jpg"));

    }

}
