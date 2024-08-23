package id.faizalempe.carousellnews.data.network

import retrofit2.http.GET

interface NewsApi {

    @GET(NEWS)
    suspend fun getNews(): List<NewsResponse>

    companion object {
        private const val NEWS = "carousell-interview-assets/android/carousell_news.json"
    }
}