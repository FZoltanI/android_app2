package com.fz.dolgozat2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

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

    public FilmsFragment() {
        // Required empty public constructor
    }

    public static FilmsFragment newInstance(String param1, String param2) {
        FilmsFragment fragment = new FilmsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    ArrayList<FilmModel> filmModels = new ArrayList<>();
    int[] filmImages = {R.drawable.avatar, R.drawable.bosszuallok, R.drawable.titanic, R.drawable.starwars, R.drawable.jw, R.drawable.oroszlankiraly, R.drawable.keresztapa, R.drawable.forrestgump, R.drawable.asotetlovag};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_films, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.filmsRecyclerView);

        setUpFilmModels();

        Film_RecyclerViewAdapter adapter = new Film_RecyclerViewAdapter(getContext(), filmModels);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setUpFilmModels(){
        String[] filmTitles = getResources().getStringArray(R.array.movie_titles);

        for (int i = 0; i < filmTitles.length; i++) {
            filmModels.add(new FilmModel(filmTitles[i], filmImages[i]));
        }
    }
}
