package com.casestudy.core.di

import android.content.Context
import com.casestudy.core.BuildConfig
import com.casestudy.core.interceptor.MockInterceptor
import com.casestudy.core.repository.DeviceRepository
import com.casestudy.core.repository.DeviceRepositoryImpl
import com.casestudy.data.remote.ApiHelper
import com.casestudy.data.remote.ApiHelperImpl
import com.casestudy.data.remote.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import javax.inject.Singleton
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.Interceptor
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ApplicationComponent::class)
class ApplicationModule {

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL


    @Provides
    @Singleton
    fun provideMockInterceptor(@ApplicationContext context: Context): Interceptor = MockInterceptor(context)

    @Provides
    @Singleton
    fun provideOkHttpClient(authInterceptor: Interceptor) = if (BuildConfig.DEBUG) {
        /**
         * Assuming this is only used for debugging purposes
         */

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideDeviceRepo(deviceRepo: DeviceRepositoryImpl): DeviceRepository = deviceRepo

}