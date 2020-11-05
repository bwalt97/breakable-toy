package com.breakable.toy.nest.controllers

import com.breakable.toy.nest.models.Movie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("api/")
class GetMovieController {

    @Value("\${api.key}")
    private lateinit var apiKey: String

    @Autowired
    lateinit var restTemplate: RestTemplate

    @RequestMapping("/{movieId}")
    fun getMovieInfo(@PathVariable("movieId") movieId: String): Movie {

        return restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/$movieId?api_key=$apiKey",
                Movie::class.java
        )
    }

}