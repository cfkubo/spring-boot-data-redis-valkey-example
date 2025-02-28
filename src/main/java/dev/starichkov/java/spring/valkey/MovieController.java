package dev.starichkov.java.spring.valkey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * REST controller for handling requests related to movie data.
 */
@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Endpoint to list all movies.
     * 
     * @return a map of all movie keys and their corresponding values
     */
    @GetMapping
    public Map<Object, Object> listAllMovies() {
        return movieService.listAllMovies();
    }
}