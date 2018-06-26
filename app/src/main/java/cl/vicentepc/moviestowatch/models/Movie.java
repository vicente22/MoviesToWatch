package cl.vicentepc.moviestowatch.models;

import com.orm.SugarRecord;

public class Movie extends SugarRecord {

    private String name;
    private boolean watched;

    public Movie() {
    }

    public Movie(String name, boolean watched) {
        this.name = name;
        this.watched = watched;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }
}