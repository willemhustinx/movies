package nl.willemhustinx.movieservice.service;

import nl.willemhustinx.movieservice.controller.MovieDTO;
import nl.willemhustinx.movieservice.controller.MovieMapper;
import nl.willemhustinx.movieservice.exception.NotFoundException;
import nl.willemhustinx.movieservice.model.Movie;
import nl.willemhustinx.movieservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository repository;
    private final MovieMapper mapper;

    @Autowired
    public MovieService(MovieRepository repository, MovieMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<MovieDTO> getAllMovies() {
        List<Movie> movieList = repository.findAll();

        return movieList.stream().map(mapper::convertToDTO).collect(Collectors.toList());
    }

    public MovieDTO getMovieById(Long movieId) {
        Movie foundMovie = repository.findByid(movieId);

        if (foundMovie != null) {
            return mapper.convertToDTO(foundMovie);
        }
        throw new NotFoundException("Movie " + movieId + " not found");
    }

    public void getMoviesByInterests(Map<String, String> interests) {
        System.out.println("werkt");
        System.out.println(interests);
    }
}
