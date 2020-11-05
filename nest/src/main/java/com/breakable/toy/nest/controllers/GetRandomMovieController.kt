package com.breakable.toy.nest.controllers

import com.breakable.toy.nest.models.UserMovieData
import com.breakable.toy.nest.models.ListOfMovie
import com.breakable.toy.nest.models.Movie
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/api")
class GetRandomMovieController {

    @Value("\${api.key}")
    private lateinit var apiKey: String

    @Value("\${api.fetch.limit}")
    private lateinit var fetchLimit: Number

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var userMovieData: UserMovieData

    @RequestMapping("/random")
    fun getRandomMovie(): Movie {

        // Temporarily setting the values here for testing
        userMovieData.addMovie("500")
        userMovieData.addMovie("520763")
        userMovieData.addMovie("330457")

        // First try to grab a recommended movie
        if (userMovieData.likedMovieIds.isNotEmpty()) {

            val movieId = userMovieData.likedMovieIds.random()

            val movie = fetchFromRecommended(movieId)

            if (movie != null) {
                userMovieData.addVisitedMovie(movie.id)
                return movie
            }
        }

        // Grab a movie from popular list
        var movie = fetchFromPopular()
        var fetchAttempts = 0;
        while (userMovieData.visitedMovieIds.contains(movie.id)) {
            movie = fetchFromPopular()

            fetchAttempts += 1
            if (fetchAttempts >= fetchLimit.toInt()) { break }
        }

        userMovieData.addVisitedMovie(movie.id)
        return movie
    }

    private fun fetchFromRecommended(movieId: String): Movie? {
        val listOfMovie = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/$movieId/recommendations?api_key=$apiKey&language=en-US&page=1",
                ListOfMovie::class.java
        )

        listOfMovie.results.forEach {
            if (!userMovieData.visitedMovieIds.contains(it.id)) {
                return it
            }
        }
        return null
    }

    private fun fetchFromPopular(): Movie {
        val page = (1..500).random()

        val listOfMovie = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/popular?api_key=$apiKey&language=en-US&page=$page",
                ListOfMovie::class.java
        )

        return listOfMovie.results.random()
    }

}
