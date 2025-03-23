package com.fz.dolgozat2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.fz.dolgozat2.database.AppDatabase;
import com.fz.dolgozat2.database.Film;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Film_RecyclerViewAdapter extends RecyclerView.Adapter<Film_RecyclerViewAdapter.MyViewHolder> {
    Context context;
    List<Film> films;
    AppDatabase db;

    public Film_RecyclerViewAdapter(Context context, List<Film> films, AppDatabase db){
        this.context = context;
        this.films = films;
        this.db = db;
    }

    @NonNull
    @Override
    public Film_RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.films_recycler_view_row, parent, false);
        return new Film_RecyclerViewAdapter.MyViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull Film_RecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(films.get(position).getFilmTitle());
        String imageUrl = films.get(position).getImage();

        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.notfound)
                .error(R.drawable.notfound)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    public void removeItem(int position) {
        Film film = films.get(position);
        db.filmDao().delete(film);
        films.remove(position);
        notifyItemRemoved(position);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        Film_RecyclerViewAdapter adapter;

        public MyViewHolder(@NonNull View itemView, Film_RecyclerViewAdapter adapter) {
            super(itemView);
            this.adapter = adapter;

            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        showDeleteConfirmationDialog(position);
                    }
                }
            });
        }

        private void showDeleteConfirmationDialog(final int position) {
            new AlertDialog.Builder(imageView.getContext())
                .setTitle("Film törlése")
                .setMessage("Biztosan törölni szeretnéd a filmet?")
                .setPositiveButton("Törlés", (dialog, which) -> {
                    adapter.removeItem(position);
                })
                .setNegativeButton("Mégsem", null)
                .show();
        }
    }
}
