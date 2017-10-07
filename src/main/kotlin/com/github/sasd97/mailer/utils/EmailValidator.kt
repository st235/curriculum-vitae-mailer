package com.github.sasd97.mailer.utils

import org.springframework.stereotype.Service
import java.util.regex.Pattern

@Service
class EmailValidator {

    private val pattern: Pattern

    init {
        pattern = Pattern.compile(EMAIL_PATTERN)
    }

    fun validate(hex: String): Boolean {
        val matcher = pattern.matcher(hex)
        return matcher.matches()
    }

    companion object {
        private val EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    }
}
