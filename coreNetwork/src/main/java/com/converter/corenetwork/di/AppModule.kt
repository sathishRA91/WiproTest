package com.converter.corenetwork.di

import com.converter.corenetwork.BuildConfig
import com.converter.corenetwork.constant.ApiConfig
import com.converter.corenetwork.data.network.ApiInterface
import com.converter.corenetwork.data.remotedatasource.RemoteDataSource
import com.converter.corenetwork.data.remotedatasource.RemoteDataSourceImpl
import com.converter.corenetwork.data.repository.CurrencyRepositoryImpl
import com.converter.corenetwork.data.repository.IbanRepositoryImpl
import com.converter.corenetwork.domain.repository.CurrencyRepository
import com.converter.corenetwork.domain.repository.IbanRepository
import com.converter.corenetwork.domain.usecase.CurrencyUseCase
import com.converter.corenetwork.domain.usecase.IbanUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by sathish on 30,April,2024
 */

@Module
@InstallIn(SingletonComponent::class)
 object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request: Request =
                    chain.request().newBuilder().addHeader("apikey", ApiConfig.CURRENCY_API).build()
                chain.proceed(request)
            })
            .connectTimeout(100L, TimeUnit.SECONDS)
            .readTimeout(100L, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConfig.DOMAIN)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }


    @Provides
    @Singleton
    fun provideDataSource(apiInterface: ApiInterface): RemoteDataSource = RemoteDataSourceImpl(apiInterface)

    @Provides
    @Singleton
    fun provideCurrencyRepository(remoteDataSource: RemoteDataSource): CurrencyRepository =
        CurrencyRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideIbanRepository(remoteDataSource: RemoteDataSource): IbanRepository = IbanRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideCurrencyUseCase(currencyRepository: CurrencyRepository): CurrencyUseCase
    {
        return CurrencyUseCase(currencyRepository)
    }

    @Provides
    @Singleton
    fun provideIbanUseCase(ibanRepository: IbanRepository): IbanUseCase
    {
        return IbanUseCase(ibanRepository)
    }
 }