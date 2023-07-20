package com.mehedi.platzistore.di

import com.mehedi.platzistore.services.AuthService
import com.mehedi.platzistore.services.ProductService
import com.mehedi.platzistore.services.UploadService
import com.mehedi.platzistore.services.UserService
import com.mehedi.platzistore.utils.AuthInterceptor
import com.mehedi.platzistore.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun provideHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }


    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())

    }


    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit.Builder): AuthService {
        return retrofit.build().create(AuthService::class.java)
    }


    @Provides
    @Singleton
    fun providesUploadService(retrofit: Retrofit.Builder): UploadService {
        return retrofit.build().create(UploadService::class.java)
    }


    @Provides
    @Singleton
    fun providesProductService(retrofit: Retrofit.Builder): ProductService {
        return retrofit.build().create(ProductService::class.java)
    }


    @Provides
    @Singleton
    fun providesUserService(retrofit: Retrofit.Builder, client: OkHttpClient): UserService {
        return retrofit.client(client).build().create(UserService::class.java)
    }


}