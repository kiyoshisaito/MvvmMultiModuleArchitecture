package com.example.mvvmmultimodulearchitecture.domain.dto

import java.text.SimpleDateFormat
import java.util.*

data class SearchHistory(
    var id: Long?,
    val searchWord: String,
    var count: Int,
    var lastSearch: Date
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SearchHistory

        if (id != other.id) return false
        if (searchWord != other.searchWord) return false
        if (count != other.count) return false
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPAN)
        if (df.format(lastSearch) != df.format(other.lastSearch)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + searchWord.hashCode()
        result = 31 * result + count
        result = 31 * result + lastSearch.hashCode()
        return result
    }
}