package com.unit.alaram.utils

import java.util.concurrent.atomic.AtomicInteger

object RandomInteger {
    private val valuee = AtomicInteger()
    fun getReandomvalue(): Int = valuee.getAndIncrement() + System.currentTimeMillis().toInt()
}