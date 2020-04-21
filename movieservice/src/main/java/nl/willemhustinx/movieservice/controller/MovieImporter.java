package nl.willemhustinx.movieservice.controller;

import nl.willemhustinx.movieservice.model.Actor;
import nl.willemhustinx.movieservice.model.Genre;
import nl.willemhustinx.movieservice.model.Movie;
import nl.willemhustinx.movieservice.model.Movies;
import nl.willemhustinx.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MovieImporter {

    private final MovieRepository repository;

    private static final Logger LOG
            = Logger.getLogger(String.valueOf(MovieRepository.class));

    @Autowired
    public MovieImporter(MovieRepository movieRepository) {
        this.repository = movieRepository;
        this.init();
    }

    private void init() {
        LOG.info("Start importing Movies");

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        File xmlFile = new File(classLoader.getResource("movies.xml").getFile());

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Movies.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Movies movies = (Movies) jaxbUnmarshaller.unmarshal(xmlFile);

            for (Movie movie : movies.getMovies()) {
                for (Genre genre : movie.getGenres()) {
                    genre.setMovie(movie);
                }
                for (Actor actor : movie.getActors()) {
                    actor.setMovie(movie);
                }
                repository.save(movie);
            }
        } catch (JAXBException e) {
            LOG.log(Level.SEVERE, "Exception!", e);
        }

        LOG.info("End importing Movies");
    }

}
