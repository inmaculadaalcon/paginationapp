package com.inmaculadaalcon.fleksy_test.ui.detail

import androidx.lifecycle.ViewModel
import com.inmaculadaalcon.fleksy_test.data.repository.TVShowsRepository
import com.inmaculadaalcon.fleksy_test.domain.model.DetailTVShow
import kotlinx.coroutines.flow.Flow

class DetailTVShowViewModel(private val tvShowsRepository: TVShowsRepository): ViewModel() {

    fun getDetailTVShow(tvShowId: Int): Flow<DetailTVShow> {
        return tvShowsRepository.getDetailTVShow(tvShowId)
    }

}