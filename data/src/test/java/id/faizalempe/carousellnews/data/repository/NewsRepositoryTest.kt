package id.faizalempe.carousellnews.data.repository

import id.faizalempe.carousellnews.data.network.NewsApi
import id.faizalempe.carousellnews.data.network.NewsResponse
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class NewsRepositoryTest {

    private val newsApi: NewsApi = mockk(relaxed = true)

    private val newsRepository by lazy {
        NewsDataRepository(newsApi)
    }

    @Test
    fun `getNews emit news`() {
        runBlocking {
            // given
            val mockNews = listOf(NewsResponse("", "", "", "", 0, 0))
            coEvery { newsApi.getNews() } returns mockNews

            // when
            newsRepository.getNews().first()

            // then
            coVerify { newsApi.getNews() }
        }
    }
}