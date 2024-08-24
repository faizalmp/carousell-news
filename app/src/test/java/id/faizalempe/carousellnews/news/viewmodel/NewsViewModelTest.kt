package id.faizalempe.carousellnews.news.viewmodel

import id.faizalempe.carousellnews.domain.ext.OnComplete
import id.faizalempe.carousellnews.domain.ext.OnError
import id.faizalempe.carousellnews.domain.ext.OnStart
import id.faizalempe.carousellnews.domain.ext.OnSuccess
import id.faizalempe.carousellnews.domain.interactor.GetNews
import id.faizalempe.carousellnews.domain.model.News
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class NewsViewModelTest {

    private val getNews: GetNews = mockk(relaxed = true)

    private val vm by lazy { NewsViewModel(getNews) }

    @Test
    fun `getNews OnStart should call NewsUiState#OnLoading`() {
        // given
        every { getNews.observe(any(), any(), any(), any(), any(), any()) } answers {
            secondArg<OnStart>().invoke()
        }

        // when
        vm.getNews()

        // then
        verify { getNews.observe(any(), any(), any(), any(), any(), any()) }
        assert(vm.uiState.value is NewsUiState.OnLoading)
        assert((vm.uiState.value as? NewsUiState.OnLoading)?.isShow == true)
    }

    @Test
    fun `getNews OnSuccess should call NewsUiState#OnSorted`() {
        // given
        val mock = listOf(News("", "", "", "", 0, 0))
        every { getNews.observe(any(), any(), any(), any(), any(), any()) } answers {
            thirdArg<OnSuccess<List<News>>>().invoke(mock)
        }

        // when
        vm.getNews()

        // then
        verify { getNews.observe(any(), any(), any(), any(), any(), any()) }
        assert(vm.uiState.value is NewsUiState.OnSorted)
        assert((vm.uiState.value as? NewsUiState.OnSorted)?.newsList == mock)
    }

    @Test
    fun `getNews OnError should call NewsUiState#OnError`() {
        // given
        val mock = Throwable()
        every { getNews.observe(any(), any(), any(), any(), any(), any()) } answers {
            arg<OnError>(3).invoke(mock)
        }

        // when
        vm.getNews()

        // then
        verify { getNews.observe(any(), any(), any(), any(), any(), any()) }
        assert(vm.uiState.value is NewsUiState.OnError)
        assert((vm.uiState.value as? NewsUiState.OnError)?.error == mock)
    }

    @Test
    fun `getNews OnComplete should call NewsUiState#OnLoading`() {
        // given
        every { getNews.observe(any(), any(), any(), any(), any(), any()) } answers {
            arg<OnComplete>(4).invoke()
        }

        // when
        vm.getNews()

        // then
        verify { getNews.observe(any(), any(), any(), any(), any(), any()) }
        assert(vm.uiState.value is NewsUiState.OnLoading)
        assert((vm.uiState.value as? NewsUiState.OnLoading)?.isShow == false)
    }

    @Test
    fun `sortRecent should call NewsUiState#OnSorted`() {
        // given
        val mock = listOf(
            News("1", "test 1", "test", "", 0, 1),
            News("2", "test 2", "test", "", 5, 2)
        )
        every { getNews.observe(any(), any(), any(), any(), any(), any()) } answers {
            thirdArg<OnSuccess<List<News>>>().invoke(mock)
        }

        // when
        vm.getNews()
        vm.sortRecent()

        // then
        verify { getNews.observe(any(), any(), any(), any(), any(), any()) }
        assert(vm.uiState.value is NewsUiState.OnSorted)
        assert((vm.uiState.value as? NewsUiState.OnSorted)?.newsList?.get(0) == mock[1])
        assert((vm.uiState.value as? NewsUiState.OnSorted)?.newsList?.get(1) == mock[0])
    }

    @Test
    fun `sortPopular should call NewsUiState#OnSorted`() {
        // given
        val mock = listOf(
            News("1", "test 1", "test", "", 0, 1),
            News("2", "test 2", "test", "", 5, 2),
            News("2", "test 2", "test", "", 5, 1)
        )
        every { getNews.observe(any(), any(), any(), any(), any(), any()) } answers {
            thirdArg<OnSuccess<List<News>>>().invoke(mock)
        }

        // when
        vm.getNews()
        vm.sortPopular()

        // then
        verify { getNews.observe(any(), any(), any(), any(), any(), any()) }
        assert(vm.uiState.value is NewsUiState.OnSorted)
        assert((vm.uiState.value as? NewsUiState.OnSorted)?.newsList?.get(0) == mock[2])
        assert((vm.uiState.value as? NewsUiState.OnSorted)?.newsList?.get(1) == mock[0])
        assert((vm.uiState.value as? NewsUiState.OnSorted)?.newsList?.get(2) == mock[1])
    }
}