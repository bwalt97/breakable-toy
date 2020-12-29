package com.breakable.toy.nest.models

class Movie {
    lateinit var id: String
    lateinit var title: String
    lateinit var overview: String
    lateinit var poster_path: String
    var adult: Boolean = false
}
