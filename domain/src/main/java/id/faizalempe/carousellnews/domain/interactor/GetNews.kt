package id.faizalempe.carousellnews.domain.interactor

import id.faizalempe.carousellnews.domain.repository.NewsRepository
import id.faizalempe.carousellnews.domain.base.BaseInteractor
import id.faizalempe.carousellnews.domain.model.News
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetNews @Inject constructor(
    private val newsRepository: NewsRepository
): BaseInteractor<List<News>, GetNews.Params>() {

    override fun build(params: Params): Flow<List<News>> = newsRepository.getNews()

    class Params
}