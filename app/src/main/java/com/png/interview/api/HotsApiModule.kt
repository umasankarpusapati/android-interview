package com.png.interview.api

import com.png.interview.api.common_model.NetworkResponseAdapterFactory
import com.png.interview.dagger.scope.ApplicationScope
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class HotsApiModule {

    @Provides
    @ApplicationScope
    fun provideHotsApi(
        okHttpClientBuilder: OkHttpClient.Builder,
        moshiBuilder: Moshi.Builder
    ): HotsApi =
        Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory
                    .create(
                        moshiBuilder.build()
                    )
            )
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .client(
                okHttpClientBuilder.build()
            )
            .baseUrl(BASE_URL)
            .build()
            .create(HotsApi::class.java)

    companion object {
        private const val BASE_URL = "http://hotsapi.net/api/v1/"
    }
}
