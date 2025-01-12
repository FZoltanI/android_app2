package com.fz.dolgozat2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Film_RecyclerViewAdapter extends RecyclerView.Adapter<Film_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<FilmModel> filmModels;

    public Film_RecyclerViewAdapter(Context context, ArrayList<FilmModel> filmModels){
        this.context = context;
        this.filmModels = filmModels;
    }

    @NonNull
    @Override
    public Film_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.films_recycler_view_row, parent, false);

        return new Film_RecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Film_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(filmModels.get(position).getFilmTitle());
        holder.imageView.setImageResource(filmModels.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return filmModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView2);
        }
    }
}
