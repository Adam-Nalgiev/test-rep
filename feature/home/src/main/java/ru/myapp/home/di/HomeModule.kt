package ru.myapp.home.di

import dagger.Module
import dagger.Provides
import ru.myapp.home.data.Repository
import ru.myapp.home.domain.GetResponseUseCase
import ru.myapp.home.presentation.View
import ru.myapp.retrofit.Retrofit1
import ru.myapp.retrofit.Retrofit2

@Module
class HomeModule {

    @Provides
    fun providesRep(obj1: Retrofit1, obj2: Retrofit2): Repository{
        return Repository(obj1, obj2)
    }

    @Provides
    fun provideUseCase(repository: Repository): GetResponseUseCase {
        return GetResponseUseCase(repository)
    }

    @Provides
    fun provideView(useCase: GetResponseUseCase): View {
        return View(useCase)
    }
}