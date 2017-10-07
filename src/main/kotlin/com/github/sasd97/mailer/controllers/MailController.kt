package com.github.sasd97.mailer.controllers

import com.github.sasd97.mailer.models.Email
import com.github.sasd97.mailer.utils.EmailValidator
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import org.springframework.web.bind.annotation.*



@RestController
class MailController(val emailValidator: EmailValidator,
                     val mailSender: MailSender) {

    @RequestMapping(value = "/email/send",
            method = arrayOf(RequestMethod.POST))
    fun sendMail(@RequestParam body: Map<String, String>): String {
        if (body["from"] == null) throw IllegalArgumentException("from field cannot be null")
        if (body["subject"] == null) throw IllegalArgumentException("subject field cannot be null")
        if (body["body"] == null) throw IllegalArgumentException("body field cannot be null")
        if (!emailValidator.validate(body["from"]!!)) throw IllegalArgumentException("from field must be an email")

        val email = Email(body["from"]!!, body["subject"]!!, body["body"]!!)

        try {
            val message = SimpleMailMessage()
            message.setTo("st235@yandex.ru")
            message.from = "st235.cv.mailer@yandex.ru"

            message.subject = email.subject
            message.text = "from ${email.from}\n${email.body}"
            mailSender.send(message)
        } catch (exception: Exception) {
            exception.printStackTrace()
            return "{\"success\": false, \"error\": 500}"
        }

        return "{\"success\": true}"
    }
}