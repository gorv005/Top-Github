package com.appstreet.top_github.modules

import com.appstreet.top_github.impls.MainImpl
import com.appstreet.top_github.interfaces.ApplicationSchedulerProvider
import com.appstreet.top_github.interfaces.SchedulerProvider
import com.appstreet.top_github.ui.githubList.MainViewModel
import com.forcast.demo.repository.MainRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module


object ViewModelModules {


    val viewModules: Module = module {
        single { ApplicationSchedulerProvider() as SchedulerProvider }

        single<MainRepository> { MainImpl(get(named(NetworkModule.RETROFIT_API))) }
        viewModel { MainViewModel(get(),get()) }

    }



}

