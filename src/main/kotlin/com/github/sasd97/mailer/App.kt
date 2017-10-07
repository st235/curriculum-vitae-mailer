package com.github.sasd97.mailer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication


@SpringBootApplication
open class App {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(App::class.java, *args)
        }
    }
}