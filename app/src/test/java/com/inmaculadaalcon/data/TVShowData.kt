package com.inmaculadaalcon.data

import com.inmaculadaalcon.common.FileReaderUtil
import com.inmaculadaalcon.pagination.data.api.model.TVShowDto
import com.inmaculadaalcon.pagination.domain.ResponseItems
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.lang.reflect.Type

object TVShowData {
    private val moshi = Moshi.Builder().build()
    private val tvShowsResponseGenericType: Type = Types.newParameterizedType(ResponseItems::class.java, TVShowDto::class.java)

    private val remoteTVShowsAdapter : JsonAdapter<ResponseItems<TVShowDto>> = moshi.adapter(
        tvShowsResponseGenericType)

    fun provideRemoteTVShowsFromAssets(): List<TVShowDto> {
        return remoteTVShowsAdapter.fromJson(
            FileReaderUtil.kotlinReadFileWithNewLineFromResources(fileName = "tvshows.json")
        )?.results?: listOf()
    }

}