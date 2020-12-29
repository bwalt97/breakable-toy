package com.breakable.toy.nest.controllers

import com.breakable.toy.nest.models.UserData
import com.breakable.toy.nest.models.ListOfMovie
import com.breakable.toy.nest.models.Movie
import com.breakable.toy.nest.models.User
import com.breakable.toy.nest.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.util.*

@CrossOrigin("http://localhost")
@RestController
@RequestMapping("/api")
class GetRandomMovieController {

    @Value("\${api.key}")
    private lateinit var apiKey: String

    @Value("\${api.fetchLimit}")
    private lateinit var fetchLimit: Number

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var userData: UserData

    var user: User = User(UUID.randomUUID().toString(), "Test One", ArrayList<String>(), ArrayList<String>())

    @RequestMapping("/random")
    fun getRandomMovie(): Movie {

        userRepository.insert(user)


        // Temporarily setting the values here for testing
//        userMovieData.likeMovie("500")
//        userMovieData.likeMovie("520763")
//        userMovieData.likeMovie("330457")

        // First try to grab a recommended movie
        if (userData.likedMovieIds.isNotEmpty()) {

            val movieId = userData.likedMovieIds.random()

            val movie = fetchFromRecommended(movieId)

            if (movie != null) {
                userData.addVisitedMovie(movie.id)
                return movie
            }
        }

        // Grab a movie from popular list
        var movie = fetchFromPopular()
        var fetchAttempts = 0;
        while (userData.visitedMovieIds.contains(movie.id) || movie.adult) {
            movie = fetchFromPopular()

            fetchAttempts += 1
            if (fetchAttempts >= fetchLimit.toInt()) { break }
        }

        userData.addVisitedMovie(movie.id)

        if (movie.adult){
            movie.id = ""
            movie.overview = ""
            movie.title = "Trouble loading new movie..."
            movie.poster_path = ""
        }

        return movie
    }

    private fun fetchFromRecommended(movieId: String): Movie? {
        val listOfMovie = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/$movieId/recommendations?api_key=$apiKey&language=en-US&page=1",
                ListOfMovie::class.java
        )

        listOfMovie.results.forEach {
            if (!userData.visitedMovieIds.contains(it.id) && !it.adult) {
                return it
            }
        }
        return null
    }

    private fun fetchFromPopular(): Movie {
        val page = (1..369).random() //500

        val listOfMovie = restTemplate.getForObject(
                "https://api.themoviedb.org/3/movie/top_rated?api_key=$apiKey&language=en-US&page=$page",
                ListOfMovie::class.java
        )

        return listOfMovie.results.random()
    }

}
