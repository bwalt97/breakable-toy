package com.breakable.toy.nest.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HelloWorldController {

    @GetMapping(value = ["sayHello"])
    fun sayHello(): String {
        return "Hello World!"
    }
}