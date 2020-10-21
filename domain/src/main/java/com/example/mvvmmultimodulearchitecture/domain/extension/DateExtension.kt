package com.example.mvvmmultimodulearchitecture.domain.extension

import java.util.*

fun Date.add(year: Int = 0, month: Int = 0, day: Int = 0, hour: Int = 0, minute: Int = 0, second: Int = 0): Date {
    val date = this
    return Calendar.getInstance().apply {
        time = date
        add(Calendar.YEAR, year)
        add(Calendar.MONTH, month)
        add(Calendar.DATE, day)
        add(Calendar.HOUR, hour)
        add(Calendar.MINUTE, minute)
        add(Calendar.SECOND, second)
    }.time
}