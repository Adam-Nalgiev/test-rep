package ru.myapp.retrofit.di

import dagger.Module
import dagger.Provides
import ru.myapp.retrofit.Retrofit1
import ru.myapp.retrofit.Retrofit2

@Module
object RetrofitModule {

    @Provides
    fun providesFirstServ(): Retrofit1 {
        return Retrofit1()
    }

    @Provides
    fun providesSecondServ(): Retrofit2 {
        return Retrofit2()
    }
}