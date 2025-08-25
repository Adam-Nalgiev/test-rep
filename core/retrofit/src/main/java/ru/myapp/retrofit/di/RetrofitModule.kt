package ru.myapp.retrofit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.myapp.retrofit.Retrofit1
import ru.myapp.retrofit.Retrofit2
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun providesFirstServ(): Retrofit1 {
        return Retrofit1
    }

    @Provides
    @Singleton
    fun providesSecondServ(): Retrofit2 {
        return Retrofit2
    }
}