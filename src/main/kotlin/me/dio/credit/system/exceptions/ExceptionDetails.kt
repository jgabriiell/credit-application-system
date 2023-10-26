package me.dio.credit.system.exceptions

import java.time.LocalDateTime

data class ExceptionDetails(
    val tittle: String,
    val timestamp: LocalDateTime,
    val status: Int,
    val exception: String,
    val details: MutableMap<String, String?>
)