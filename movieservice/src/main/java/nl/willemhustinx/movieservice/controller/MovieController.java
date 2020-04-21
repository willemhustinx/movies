package nl.willemhustinx.movieservice.controller;

import nl.willemhustinx.movieservice.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/movies")
public class MovieController {

    MovieService service;

    @Autowired
    public MovieController(MovieService movieService) {
        this.service = movieService;
    }


    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> list = service.getAllMovies();
        return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
    }



    @GetMapping("/single/{movieID}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long movieID) {
        MovieDTO movie = service.getMovieById(movieID);

        return new ResponseEntity<>(movie, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/iets/ja")
    public ResponseEntity<MovieDTO> getMovie() {
        System.out.println("1 film");
        MovieDTO movie = service.getMovieById(1L);

        return new ResponseEntity<>(movie, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("interest")
    public ResponseEntity<MovieDTO> getMoviesByInterests(){

        List<MovieDTO> list = service.getAllMovies();

        System.out.println("interests");

        return new ResponseEntity<>(list.get(0), new HttpHeaders(), HttpStatus.OK);
    }
}
