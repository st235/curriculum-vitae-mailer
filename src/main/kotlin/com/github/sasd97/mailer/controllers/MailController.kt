package com.github.sasd97.mailer.controllers

import com.github.sasd97.mailer.models.Email
import com.github.sasd97.mailer.utils.EmailValidator
import org.springframework.web.bind.annotation.*


@RestController
class MailController(val emailValidator: EmailValidator) {

    @RequestMapping(value = "/email/send",
            method = arrayOf(RequestMethod.POST))
    fun sendMail(@RequestParam body: Map<String, String>): String {
        if (body["to"] == null) throw IllegalArgumentException("to field cannot be null")
        if (body["title"] == null) throw IllegalArgumentException("to field cannot be null")
        if (body["body"] == null) throw IllegalArgumentException("to field cannot be null")
        if (!emailValidator.validate(body["to"]!!)) throw IllegalArgumentException("to field must be an email")

        val email = Email(body["to"]!!, body["title"]!!, body["body"]!!)

        System.out.println(email.email)
        System.out.println(email.title)
        System.out.println(email.body)
        return "Greetings from Spring Boot!"
    }
}