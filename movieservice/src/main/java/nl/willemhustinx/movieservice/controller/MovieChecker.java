package nl.willemhustinx.movieservice.controller;

import nl.willemhustinx.movieservice.model.Actor;
import nl.willemhustinx.movieservice.model.Genre;
import nl.willemhustinx.movieservice.model.Movie;

import java.util.Set;

public class MovieChecker {

    public boolean hasActor(Movie movie, String actorName) {
        Set<Actor> actors = movie.getActors();
        for (Actor actor : actors) {
            if (actor.getName().equals(actorName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasActorWithGender(Movie movie, String gender) {
        Set<Actor> actors = movie.getActors();
        for (Actor actor : actors) {
            if (actor.getGender().equals(gender)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasGenre(Movie movie, String genreName) {
        Set<Genre> genres = movie.getGenres();
        for (Genre genre : genres) {
            if (genre.getGenre().equals(genreName)) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRating(Movie movie, String rating) {
        if (rating.charAt(rating.length() - 1) == '+') {
            rating = rating.replace(rating.substring(rating.length() - 1), "");
            int ratingnumber = Integer.parseInt(rating);
            if (movie.getRating() > ratingnumber) {
                return true;
            }
        }
        return false;
    }

    public boolean hasRuntime(Movie movie, String runtime) {
        if (runtime.charAt(0) == '<') {
            String[] runtimeWords = runtime.split(" ");
            int runtimeInt = Integer.parseInt(runtimeWords[1]);
            if (movie.getRuntime() > runtimeInt) {
                return true;
            }
        }
        return false;
    }

}
