package id.faizalempe.carousellnews.data.repository

import id.faizalempe.carousellnews.data.mapper.toNewsList
import id.faizalempe.carousellnews.data.network.NewsApi
import id.faizalempe.carousellnews.domain.model.News
import id.faizalempe.carousellnews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsDataRepository @Inject constructor(private val newsApi: NewsApi): NewsRepository {

    override fun getNews(): Flow<List<News>> = flow {
        val news = newsApi.getNews().toNewsList()
        emit(news)
    }
}