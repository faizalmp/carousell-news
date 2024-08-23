package id.faizalempe.carousellnews.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.faizalempe.carousellnews.data.repository.NewsDataRepository
import id.faizalempe.carousellnews.domain.repository.NewsRepository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsDataRepository: NewsDataRepository
    ): NewsRepository = newsDataRepository
}