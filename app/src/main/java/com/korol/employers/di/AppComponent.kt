package com.korol.employers.di

import com.korol.employers.ui.splash.StartActivity
import com.korol.employers.ui.work.filters.FiltersFragment
import com.korol.employers.ui.work.list.ListEmployersFragment
import dagger.Component

@Component(modules = [AppModule::class,DomainModule::class,DataModule::class])
interface AppComponent {

    fun injectSplashActivity(splashActivity: StartActivity)
    fun injectListEmployersFragment(listEmployersFragment: ListEmployersFragment)
    fun injectFiltersFragment(filtersFragment: FiltersFragment)
}