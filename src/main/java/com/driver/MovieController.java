package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService ;
    @PostMapping("/add_movie")
    public ResponseEntity addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return new ResponseEntity<>("New movie added successfully", HttpStatus.CREATED) ;
    }

    @PostMapping("/add_director")
    public ResponseEntity addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        return new ResponseEntity<>("New director added successfully",HttpStatus.CREATED);
    }

    @PutMapping("/add_movie_director_pair")
    public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movie , @RequestParam("director") String director){
        movieService.createMovieDirectorPair(movie,director);
        return new ResponseEntity<>("New movie-director pair added successfully",HttpStatus.CREATED) ;
    }

    @GetMapping("/get_movie_by_name/{name}")
    public ResponseEntity getMovieByName(@PathVariable String name){
        Movie movie = movieService.findMovie(name) ;
        return new ResponseEntity<>(movie,HttpStatus.CREATED) ;
    }
    @GetMapping("/get_director_by_name/{name}")
    public ResponseEntity getDirectorByName(@PathVariable String name){
        Director director = movieService.findDirector(name) ;
        return new ResponseEntity<>(director,HttpStatus.CREATED) ;
    }

    @GetMapping("get_movie_by_director_name/{director}")
    public ResponseEntity getMoviesByDirecorName(@PathVariable String director){
        List<String> movie = movieService.findMoviesFromDirector(director) ;
        return new ResponseEntity<>(movie,HttpStatus.CREATED) ;
    }

    @GetMapping("/get_all_movies")
    public ResponseEntity findAllMovies(){
        List<String> movies = movieService.findAllMovies() ;
        return new ResponseEntity<>(movies,HttpStatus.CREATED) ;
    }

    @DeleteMapping("/delete_director_by_name")
    public ResponseEntity deleteDirectorByName(@RequestParam String director){
        movieService.deleteDirector(director);
        return new ResponseEntity<>(director +"removed successfully",HttpStatus.ACCEPTED) ;
    }

    @DeleteMapping("/delete_all_directors")
    public ResponseEntity deleteAllDirectors (){
        movieService.deleteAllDirector();
        return new ResponseEntity<>("All directors are deleted successfully",HttpStatus.ACCEPTED) ;
    }
}