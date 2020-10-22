package com.png.interview.api

import com.png.interview.api.common_model.ErrorResponse
import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.api.models.heroes.Hero
import retrofit2.http.GET

interface HotsApi {
    @GET("heroes/")
    suspend fun getAllHeroes(): NetworkResponse<List<Hero>, ErrorResponse>
}