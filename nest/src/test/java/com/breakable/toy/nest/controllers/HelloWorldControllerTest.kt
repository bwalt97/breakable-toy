package com.breakable.toy.nest.controllers

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
class HelloWorldControllerTest {

    lateinit var helloWorldController: HelloWorldController

    lateinit var mockMvc: MockMvc

    private val requestBuilder: RequestBuilder = MockMvcRequestBuilders.get("/sayHello")

    @Before
    fun setup() {
        helloWorldController = HelloWorldController()

        mockMvc = MockMvcBuilders
                .standaloneSetup(helloWorldController)
                .build()
    }

    @Test
    fun testHelloWorldController_sayHello() {
        // When
        mockMvc.perform(requestBuilder)
                // Then
                .andExpect(status().isOk)
                .andExpect(content().string("Hello World!"))
    }
}
