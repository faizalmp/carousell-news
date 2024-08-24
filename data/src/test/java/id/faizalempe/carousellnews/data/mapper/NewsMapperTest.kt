package id.faizalempe.carousellnews.data.mapper

import id.faizalempe.carousellnews.data.network.NewsResponse
import id.faizalempe.carousellnews.domain.model.News
import org.junit.Test

class NewsMapperTest {

    @Test
    fun `toNews return news`() {
        // given
        val mockNewsResponse = NewsResponse("", "", "", "", 0, 0)
        val expectedNews = News("", "", "", "", 0, 0)

        // when
        mockNewsResponse.toNews()

        // then
        assert(mockNewsResponse.id == expectedNews.id)
        assert(mockNewsResponse.rank == expectedNews.rank)
        assert(mockNewsResponse.timeCreated == expectedNews.timeCreated)
        assert(mockNewsResponse.title == expectedNews.title)
        assert(mockNewsResponse.desc == expectedNews.desc)
        assert(mockNewsResponse.bannerUrl == expectedNews.bannerUrl)
    }

    @Test
    fun `toNewsList return newsList`() {
        // given
        val mockNewsResponseList = listOf(NewsResponse("", "", "", "", 0, 0))
        val expectedNewsList = listOf(News("", "", "", "", 0, 0))

        // when
        mockNewsResponseList.toNewsList()

        // then
        assert(mockNewsResponseList.size == expectedNewsList.size)
        assert(mockNewsResponseList[0].id == expectedNewsList[0].id)
        assert(mockNewsResponseList[0].rank == expectedNewsList[0].rank)
        assert(mockNewsResponseList[0].timeCreated == expectedNewsList[0].timeCreated)
        assert(mockNewsResponseList[0].title == expectedNewsList[0].title)
        assert(mockNewsResponseList[0].desc == expectedNewsList[0].desc)
        assert(mockNewsResponseList[0].bannerUrl == expectedNewsList[0].bannerUrl)
    }
}