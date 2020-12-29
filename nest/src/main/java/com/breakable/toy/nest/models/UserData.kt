package com.breakable.toy.nest.models
import org.springframework.stereotype.Component

@Component
class UserData {

    var likedMovieIds: ArrayList<String> = ArrayList()

    var visitedMovieIds: ArrayList<String> = ArrayList()

    fun likeMovie(id: String) {
        likedMovieIds.add(id)
    }

    fun addVisitedMovie(id: String) {
        visitedMovieIds.add(id)
    }

}
