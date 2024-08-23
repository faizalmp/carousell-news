package id.faizalempe.carousellnews.domain.model

import android.text.format.DateUtils

data class News(
    val id: String?,
    val title: String?,
    val desc: String?,
    val bannerUrl: String?,
    val timeCreated: Long?,
    val rank: Long?
) {
    val timeAgo: String get() =
        timeCreated?.let { it.toString() }.orEmpty()
}