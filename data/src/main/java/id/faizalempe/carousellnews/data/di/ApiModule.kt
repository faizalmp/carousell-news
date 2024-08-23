package id.faizalempe.carousellnews.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.faizalempe.carousellnews.data.network.NewsApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ApiModule {

    @Singleton
    @Provides
    fun provideNewsApi(
        retrofitBuilder: Retrofit.Builder,
        okhttpClientBuilder: OkHttpClient.Builder
    ): NewsApi = retrofitBuilder
        .baseUrl("https://storage.googleapis.com/")
        .client(okhttpClientBuilder.build())
        .build()
        .create(NewsApi::class.java)
}