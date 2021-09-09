package com.inmaculadaalcon.pagination.data.api.rest

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.inmaculadaalcon.pagination.data.api.model.SimilarTVShowItemDto
import com.inmaculadaalcon.pagination.data.api.model.TVShowDto
import com.inmaculadaalcon.pagination.domain.ResponseItems
import com.inmaculadaalcon.pagination.domain.model.TVShow
import com.inmaculadaalcon.pagination.domain.model.toDto
import retrofit2.HttpException
import java.io.IOException

class SimilarTVShowsPagingSource(private val rest: MovieDBRest, private val showId: Int, private val tvShow: TVShow): PagingSource<Int, TVShowDto>() {

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
            val response = rest.getSimilarTVShows(tvId= showId, language = "en-US", page = pageIndex)
            val tvshows = response.results
            val mutable = tvshows.toMutableList()
            mutable.add(0, tvShow.toDto())
            LoadResult.Page(
                data = mutable.toList(),
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