package id.faizalempe.carousellnews.news.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import id.faizalempe.carousellnews.domain.interactor.GetNews
import id.faizalempe.carousellnews.domain.model.News
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNews: Lazy<GetNews>
): ViewModel() {

    private val _uiState = MutableStateFlow<NewsUiState>(NewsUiState.None)
    val uiState: StateFlow<NewsUiState> = _uiState.asStateFlow()

    private var news: List<News> = listOf()

    fun getNews() {
        getNews.get().observe(
            params = GetNews.Params(),
            onStart = { updateUiState(NewsUiState.OnLoading(isShow = true)) },
            onSuccess = { news: List<News> ->
                this.news = news
                sortRecent()
            },
            onError = { error: Throwable -> updateUiState(NewsUiState.OnError(error)) },
            onComplete = { updateUiState(NewsUiState.OnLoading(isShow = false)) },
            scope = viewModelScope
        )
    }


    fun sortRecent() {
        val sortedNews = news.sortedByDescending { it.timeCreated }
        updateUiState(NewsUiState.OnSorted(sortedNews))
    }

    fun sortPopular() {
        val sortedNews = news.sortedByDescending { it.timeCreated }.sortedBy { it.rank }
        updateUiState(NewsUiState.OnSorted(sortedNews))
    }

    private fun updateUiState(state: NewsUiState) = _uiState.update { state }
}