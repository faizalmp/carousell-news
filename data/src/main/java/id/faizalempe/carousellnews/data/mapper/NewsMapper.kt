package id.faizalempe.carousellnews.data.mapper

import id.faizalempe.carousellnews.data.network.NewsResponse
import id.faizalempe.carousellnews.domain.model.News

fun NewsResponse.toNews() = News(
    id = id,
    title = title,
    desc = desc,
    bannerUrl = bannerUrl,
    timeCreated = timeCreated,
    rank = rank
)

fun List<NewsResponse>.toNewsList(): List<News> = map { it.toNews() }