package com.inmaculadaalcon.fleksy_test.data.api.rest

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.inmaculadaalcon.fleksy_test.data.api.model.TVShowDto
import com.inmaculadaalcon.fleksy_test.data.datasource.TVShowDataSourceImpl.Companion.SERVICE_PAGE_SIZE
import retrofit2.HttpException
import java.io.IOException

class TVShowsPagingSource(private val rest: MovieDBRest): PagingSource<Int, TVShowDto>() {

    companion object {
        const val FIRST_PAGE_INDEX = 1
    }

    override fun getRefreshKey(state: PagingState<Int, TVShowDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVShowDto> {
        val pageIndex = params.key ?: FIRST_PAGE_INDEX
        return try {
            val response = rest.getTopRatedTV( language = "en-US", page = pageIndex)
            val tvshows = response.results
            LoadResult.Page(
                data = tvshows,
                prevKey =  if (pageIndex == FIRST_PAGE_INDEX) null else pageIndex,
                nextKey = pageIndex + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}