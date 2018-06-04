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
public class UpcomingFragment extends Fragment {

    @BindView(R.id.now_rvmovie)
    RecyclerView now_rvmovie;
    private List<Movie> movies;

    public UpcomingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming, container, false);
        ButterKnife.bind(this,view);
        intitializeData();
        LinearLayoutManager llm=new LinearLayoutManager(this.getContext());
        now_rvmovie.setHasFixedSize(true);
        now_rvmovie.setLayoutManager(llm);

        MovieRvAdapter movieRvAdapter=new MovieRvAdapter(getContext(),movies);
        now_rvmovie.setAdapter(movieRvAdapter);

        return view;

    }

    private void intitializeData() {
        movies=new ArrayList<>();
        movies.add(new Movie("277834", "Shannara", "Shannara /ˈʃænərə/[1] is a series of high fantasy[2] novels written by Terry Brooks, beginning with The Sword of Shannara in 1977 and continuing through The Black Elfstone which was released in June 2017;", 6.5f, 854, "https://roadshow.com.au/-/media/images/roadshow/movies/wb/2017/shannara-chronicles-s2/shannara_chronicles_the_s2_featured_315x470.ashx", "http://legionofleia.com/wp-content/uploads/2016/02/the-shannara-chronicles_banner-1.jpg"));
        movies.add(new Movie("121856", "Dinosaur Island (2014)", "Dinosaur Island (also known as Journey to Dinosaur Island in the UK) is a 2014 British-Australian family adventure film. The film is written and directed by Emmy Winner", 6.2f, 745, "https://i.pinimg.com/736x/9b/b0/d4/9bb0d4d29d3b64acfe81bccfd14491e5--adventure-movies-english-movies.jpg", "https://www.myanmore.com/yangon/wp-content/uploads/sites/2/2015/01/Dinosour1.jpg"));
        movies.add(new Movie("330459", "The Maze Runner", "The Maze Runner is a 2014 American dystopian science fiction action thriller film directed by Wes Ball, in his directorial debut, based on James Dashner's 2009 novel of the same name.", 5.3f, 691, "https://img.reelgood.com/content/movie/eaba1ea7-6c7c-4b7c-9e32-f8ae36f04953/poster-342.jpg", "https://i.ebayimg.com/images/g/ntoAAOSwMfhaW5PP/s-l300.jpg"));
        movies.add(new Movie("283366", "Storm", "Geostorm is a 2017 American disaster film[1] directed, co-written, and produced by Dean Devlin as his feature film directorial debut. The film stars Gerard Butler, Jim Sturgess, Ed Harris, Abbie Cornish, Richard Schiff, Alexandra Maria Lara, Robert Sheehan, Daniel Wu, Eugenio Derbez, and Andy García.", 7.2f, 1802, "https://cdn2.christiancinema.com/images/products/7973_255w_360h.jpg", "http://cdn.darkhorizons.com/wp-content/uploads/2017/08/warners-premieres-the-geostorm-poster.jpg"));
    }

}

