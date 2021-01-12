package com.example.bookmyshow.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookmyshow.exception.ResourceNotFoundException;
import com.example.bookmyshow.interfaces.MoviesInterface;
import com.example.bookmyshow.pojos.Movie;
//To enable CORS on the server, add a @CrossOrigin annotation
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class MovieController {
	
	
	@Autowired
	private MoviesInterface moviesInterface;
	
	@GetMapping("/movies")
	public List<Movie> getMovies(){
		return moviesInterface.findAll();
	}
	
	@GetMapping("/movies/{id}")
//	ResponseEntity represents whole Http response:status code, headers, and body.
	//we use it to configure the response
    public ResponseEntity<Movie> getMoviesById(@PathVariable(value = "id") Long movieId)throws ResourceNotFoundException {
    
        Movie movie = moviesInterface.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + movieId));;
        return ResponseEntity.ok().body(movie);
    }
	
	@PostMapping("/movies")
	public Movie createMovie(@Validated @RequestBody Movie movie){
		return moviesInterface.save(movie);
	}
	
	@PutMapping("/movies/{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable(value = "id") Long movieId,@Validated @RequestBody Movie movie) throws ResourceNotFoundException {
		Movie movies=moviesInterface.findById(movieId)
				 .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + movieId));
		movies.setName(movie.getName());
		movies.setGenere(movies.getGenere());
		movies.setLanguage(movie.getLanguage());
		
		final Movie updatedMovie=moviesInterface.save(movies);
//		A shortcut for creating a ResponseEntity with the given body and the status set to OK.
		return ResponseEntity.ok(updatedMovie);
		
	}
	@DeleteMapping("/movies/{id}")
    public Map<String, Boolean> deleteMovie(@PathVariable(value = "id") Long movieId)
         throws ResourceNotFoundException {
        Movie movie = moviesInterface.findById(movieId)
       .orElseThrow(() -> new ResourceNotFoundException("Movie not found for this id :: " + movieId));

        moviesInterface.delete(movie);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
