package dev.pimentel.feed

import dev.pimentel.feed.data.repository.FeedRepository
import dev.pimentel.feed.data.repository.FeedRepositoryImpl
import dev.pimentel.feed.data.source.FeedDataSource
import dev.pimentel.feed.data.usecases.FetchHeadlines
import dev.pimentel.feed.presentation.FeedViewModel
import dev.pimentel.navigator.Navigator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private val dataSourcesModule = module {
    single { get<Retrofit>().create(FeedDataSource::class.java) }
}

private val repositoryModule = module {
    single<FeedRepository> { FeedRepositoryImpl(get()) }
}

private val useCasesModule = module {
    single { FetchHeadlines(get()) }
}

private val viewModelModule = module {
    viewModel { FeedViewModel(get<Navigator>(), get(), get()) }
}

val feedModules = listOf(
    dataSourcesModule,
    repositoryModule,
    useCasesModule,
    viewModelModule
)
