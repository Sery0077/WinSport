package sery.vlasenko.winsport.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import sery.vlasenko.winsport.BuildConfig
import java.util.concurrent.TimeUnit

object NetworkModule {
    val apiService: ApiInterface = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(
            provideMoshiConverterFactory()
        )
        .client(provideOkHttpClient())
        .build()
        .create(ApiInterface::class.java)

    private fun provideMoshiConverterFactory(): MoshiConverterFactory = MoshiConverterFactory
        .create()
        .failOnUnknown()

    private fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .readTimeout(5, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .addInterceptor(provideOkHttpLoggingInterceptor())
        .build()

    private fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
}