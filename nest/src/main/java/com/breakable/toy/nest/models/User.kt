package com.breakable.toy.nest.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "users")
class User(@Id val id:String? = null, val name:String, var visitedMovies:ArrayList<String>, var likedMovies:ArrayList<String>){

    fun likeMovie(id: String) {
        likedMovies.add(id)
    }

    fun addVisitedMovie(id: String) {
        visitedMovies.add(id)
    }

}
