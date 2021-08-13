package com.inmaculadaalcon.fleksy_test.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inmaculadaalcon.fleksy_test.domain.usecase.GetTopRatedTv
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(private val getTopRatedTv: GetTopRatedTv): ViewModel() {

  fun getTopRatedTV(page: Int) {
    viewModelScope.launch {
      try{
        getTopRatedTv.getTopRatedTVShows(page).collect {
          println("RUINA Recoge algo???? -> $it")
         // _topRatedStateFlow.value = it.
        }
      }catch (e: Exception) {
        println("RUINA Recoge algo???? -> ")
        e.printStackTrace()
      }
    }
  }


}
