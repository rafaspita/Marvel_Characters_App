package com.spitaliere.data.api

import com.spitaliere.data.api.response.ApiResponse
import com.spitaliere.data.api.response.CharacterResult
import com.spitaliere.data.api.response.ComicsResults
import com.spitaliere.data.api.response.Data
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
interface MarvelApi {

    @GET("characters")
    fun getCharacters(
        @Query("offset") offset: String
    ): Single<ApiResponse<Data<CharacterResult>>>

    @GET("characters/{characterId}/comics")
    fun getComics(
        @Path("characterId") characterId:String
    ): Single<ApiResponse<Data<ComicsResults>>>
}