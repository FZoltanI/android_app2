package com.fz.dolgozat2;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fz.dolgozat2.database.AppDatabase;
import com.fz.dolgozat2.database.Film;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FilmsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilmsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private List<Film> films;
    private AppDatabase db;


    public FilmsFragment() {}

    public static FilmsFragment newInstance(String param1, String param2) {
        FilmsFragment fragment = new FilmsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    //ArrayList<Film> films = new ArrayList<>();
    //int[] filmImages = {R.drawable.avatar, R.drawable.bosszuallok, R.drawable.titanic, R.drawable.starwars, R.drawable.jw, R.drawable.oroszlankiraly, R.drawable.keresztapa, R.drawable.forrestgump, R.drawable.asotetlovag};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_films, container, false);
    }

    private Film_RecyclerViewAdapter adapter;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        db = AppDatabase.getInstance(view.getContext());

        RecyclerView recyclerView = view.findViewById(R.id.filmsRecyclerView);

        //setUpFilmModels();

        //List<Film> films = new ArrayList<>();
        String user = getActivity().getIntent().getStringExtra("name");
        films = db.filmDao().getAllFilms();

        adapter = new Film_RecyclerViewAdapter(getContext(), films, db);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void loadFilms() {
        String user = getActivity().getIntent().getStringExtra("user");

        films.clear();
        films.addAll(db.filmDao().getAllFilms());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();

        loadFilms();
    }

    /*
    private void setUpFilmModels(){
        String[] filmTitles = getResources().getStringArray(R.array.movie_titles);

        films.add(new Film("Oppenheimer", "https://posters.movieposterdb.com/24_03/2023/15398776/l_oppenheimer-movie-poster_28ad1035.jpg"));
        films.add(new Film("Captain America: Brave New World", "https://posters.movieposterdb.com/24_12/2025/14513804/l_captain-america-brave-new-world-movie-poster_d309c549.jpg"));
        films.add(new Film("Avengers: Endgame", "https://posters.movieposterdb.com/23_06/2019/4154796/l_avengers-endgame-movie-poster_21dfd2d6.jpg"));

        for (int i = 0; i < filmTitles.length; i++) {
            films.add(new Film(filmTitles[i], filmImages[i]));
        }
    }
    */
}
