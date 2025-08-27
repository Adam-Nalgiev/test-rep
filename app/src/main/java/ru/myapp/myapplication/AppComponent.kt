package ru.myapp.myapplication

import dagger.Component
import ru.myapp.home.di.HomeModule
import ru.myapp.home.presentation.View
import ru.myapp.retrofit.di.RetrofitModule

@Component(
    modules = [RetrofitModule::class, HomeModule::class]
)
interface AppComponent {
    fun provideView(): View
}