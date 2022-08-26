package com.mariano.hellofriendsapp.network.modules

import com.mariano.hellofriendsapp.network.apiService.UserServices
import com.mariano.hellofriendsapp.network.utilsNetwork.UserInterceptor
import com.mariano.hellofriendsapp.utils.models.FailureResponse
import com.mariano.hellofriendsapp.utils.models.PolyResponse
import com.mariano.hellofriendsapp.utils.models.SuccessResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.onenowy.moshipolymorphicadapter.ValuePolymorphicAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ViewModelComponent::class)
object NetworkModule {

    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideOKHttp(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(UserInterceptor())
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    fun providePolymorphicAdapterFactory (): ValuePolymorphicAdapterFactory<PolyResponse, Boolean> =
        ValuePolymorphicAdapterFactory.of(PolyResponse::class.java, "status", Boolean::class.java)
            .withSubtype(SuccessResponse::class.java, true)
            .withSubtype(FailureResponse::class.java, false)

    @Provides
    fun provideKotlinJsonAdapterFactory(): KotlinJsonAdapterFactory = KotlinJsonAdapterFactory()

    @Provides
    fun provideMoshi(
        kotlinJsonAdapterFactory: KotlinJsonAdapterFactory,
        polymorphicAdapterFactory: ValuePolymorphicAdapterFactory<PolyResponse, Boolean>
    ): Moshi = Moshi.Builder()
        .add(polymorphicAdapterFactory)
        .add(kotlinJsonAdapterFactory)
        .build()

    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi): MoshiConverterFactory =
        MoshiConverterFactory.create(moshi)

    @Provides
    fun provideRetrofitClient(
        URL: String,
        okHttp: OkHttpClient,
        moshiConverterFactory: MoshiConverterFactory
    ): Retrofit = Retrofit.Builder()
        .addConverterFactory(moshiConverterFactory)
        .client(okHttp)
        .baseUrl(URL)
        .build()

    //-----------------------------------------------------------------------------------//

    @Provides
    fun provideUserService(serviceManager: Retrofit): UserServices =
        serviceManager.create(UserServices::class.java)
}