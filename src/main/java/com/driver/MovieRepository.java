package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {

    HashMap<String ,Movie> movieMap = new HashMap<>();
    HashMap<String,Director> directorMap = new HashMap<>() ;
    HashMap<String, List<String>> directorMovieMap = new HashMap<>() ;

    public void addMovie(Movie movie){
        String name = movie.getName();
        movieMap.put(name,movie) ;
    }
    public void addDirector(Director director){
        String name = director.getName();
        directorMap.put(name,director) ;
    }

    public void addMovieDirectorPair(String movie ,String director){
        if(movieMap.containsKey(movie) && directorMap.containsKey(director)){
            List<String> currMovie = new ArrayList<>() ;
            if(directorMovieMap.containsKey(director)) currMovie = directorMovieMap.get(director) ;
            currMovie.add(movie) ;
            directorMovieMap.put(director,currMovie) ;
        }
    }
    public Movie getMovie(String movie){
        return movieMap.get(movie) ;
    }

    public Director getDirector(String director){
        return directorMap.get(director) ;
    }

    public List<String> getMoviesFromDirector(String director){
        List<String> movieList = new ArrayList<>() ;
        if(directorMovieMap.containsKey(director)){
            movieList = directorMovieMap.get(director) ;
        }
        return movieList ;
    }

    public List<String> getAllMovies(){
        return new ArrayList<>(movieMap.keySet()) ;
    }

    public void deleteDirector(String director){
        List<String> movies = new ArrayList<>() ;
        if(directorMovieMap.containsKey(director)){
            movies = directorMovieMap.get(director);
            for(String movie : movies){
                if(movieMap.containsKey(movie)){
                    movieMap.remove(movie);
                }
            }
            directorMovieMap.remove(director) ;
        }
        if(directorMap.containsKey(director)){
            directorMovieMap.remove(director) ;
        }
    }
    public void deleteAllDirector(){
        HashSet<String> movieSet = new HashSet<>() ;

        for(String director : directorMovieMap.keySet()){
            for(String movie : directorMovieMap.get(director)){
                movieSet.add(movie) ;
            }
        }
        for(String movie :movieSet){
            if(movieMap.containsKey(movie)){
                movieMap.remove( movie);
            }
        }
    }

}
