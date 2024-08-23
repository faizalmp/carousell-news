package id.faizalempe.carousellnews.news.viewmodel

import id.faizalempe.carousellnews.domain.model.News

sealed class NewsUiState {

    object None : NewsUiState()

    class OnError(val error: Throwable) : NewsUiState()

    class OnLoading(val isShow: Boolean) : NewsUiState()

    class OnSorted(val newsList: List<News>) : NewsUiState()
}

interface NewsUiStateAction {

    fun doOnError(error: Throwable)

    fun doOnLoading(isShow: Boolean)

    fun doOnSorted(newsList: List<News>)
}