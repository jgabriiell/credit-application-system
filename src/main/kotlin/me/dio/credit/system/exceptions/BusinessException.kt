package me.dio.credit.system.exceptions

data class BusinessException(override val message: String?) : RuntimeException(message)