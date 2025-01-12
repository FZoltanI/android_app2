package com.fz.dolgozat2;

public class FilmModel {
    String filmTitle;
    int image;

    public FilmModel(String filmTitle, int image) {
        this.filmTitle = filmTitle;
        this.image = image;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public int getImage() {
        return image;
    }
}
