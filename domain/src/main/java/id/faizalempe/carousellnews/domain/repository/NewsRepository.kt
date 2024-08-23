package id.faizalempe.carousellnews.domain.repository

import id.faizalempe.carousellnews.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(): Flow<List<News>>
}