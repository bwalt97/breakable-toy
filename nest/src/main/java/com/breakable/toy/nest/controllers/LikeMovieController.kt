package com.breakable.toy.nest.controllers

import com.breakable.toy.nest.models.UserData
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@CrossOrigin("http://localhost")
@RestController
@RequestMapping("/api")
class LikeMovieController {

    @Value("\${api.key}")
    private lateinit var apiKey: String

    @Autowired
    lateinit var restTemplate: RestTemplate

    @Autowired
    lateinit var userData: UserData

    // Will need to add user Id as a path variable later ...
    @PatchMapping("/like/{movieId}")
    fun updateLikedMovies(@PathVariable movieId: String) {
        userData.likeMovie(movieId);
    }
}
