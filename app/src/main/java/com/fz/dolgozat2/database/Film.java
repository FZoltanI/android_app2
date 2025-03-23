package com.fz.dolgozat2.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "film")
public class Film {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String image;

    public Film() {
    }

    public Film(String filmTitle, String image) {
        this.title = filmTitle;
        this.image = image;
    }

    public int getId(){
        return id;
    }

    public String getFilmTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
