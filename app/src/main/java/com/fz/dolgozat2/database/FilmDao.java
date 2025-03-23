package com.fz.dolgozat2.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FilmDao {
    @Insert
    void insert(Film film);

    @Query("SELECT * FROM film")
    List<Film> getAllFilms();

    @Delete
    void delete(Film film);
}
