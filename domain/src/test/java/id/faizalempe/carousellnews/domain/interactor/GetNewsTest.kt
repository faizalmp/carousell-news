package id.faizalempe.carousellnews.domain.interactor

import id.faizalempe.carousellnews.domain.repository.NewsRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class GetNewsTest {

    private val repository: NewsRepository = mockk(relaxed = true)

    private val getNews by lazy { GetNews(repository) }

    @Test
    fun `build should execute getNews`() {
        // given

        // when
        getNews.build(GetNews.Params())

        // then
        verify { repository.getNews() }
    }
}