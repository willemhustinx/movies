package nl.willemhustinx.movieservice.controller;

import nl.willemhustinx.movieservice.model.Movie;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MovieMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public Movie convertToNewEntity(final MovieDTO movieDTO) {
        return modelMapper.map(movieDTO, Movie.class);
    }

    public void convertToUpdatedEntity(final MovieDTO movieDTO, final Movie movie) {
        modelMapper.map(movieDTO, movie);
    }

    public MovieDTO convertToDTO(final Movie movie) {
        return modelMapper.map(movie, MovieDTO.class);
    }
}
