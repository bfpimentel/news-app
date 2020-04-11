package dev.pimentel.core

import dev.pimentel.core.schedulerprovider.SchedulerProvider
import dev.pimentel.core.schedulerprovider.SchedulerProviderImpl
import dev.pimentel.core.usecases.GetErrorMessage
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val REQUEST_TIMEOUT = 60L

private val networkModule = module {
    single {
        val apiUrl = androidContext().getString(R.string.news_api_url)
        val apiKey = androidContext().getString(R.string.news_api_key)

        OkHttpClient.Builder()
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(RequestInterceptor(apiKey))
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
            .let { client ->
                Retrofit.Builder()
                    .baseUrl(apiUrl)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build()

            }
    }
}

private val useCasesModule = module {
    single { GetErrorMessage(androidContext()) }
}

private val schedulerProviderModule = module {
    single<SchedulerProvider> { SchedulerProviderImpl() }
}

val coreModules = listOf(
    useCasesModule,
    schedulerProviderModule,
    networkModule
)

internal class RequestInterceptor(
    private val apiKey: String
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        return originalRequest.newBuilder()
            .addHeader(AUTHORIZATION_HEADER_NAME, apiKey)
            .build()
            .let(chain::proceed)
    }

    private companion object {
        const val AUTHORIZATION_HEADER_NAME = "Authorization"
    }
}
