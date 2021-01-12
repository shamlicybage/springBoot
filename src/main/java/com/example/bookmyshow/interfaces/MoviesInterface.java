package com.example.bookmyshow.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmyshow.pojos.Movie;

@Repository
public interface MoviesInterface extends JpaRepository<Movie, Long>{

}
