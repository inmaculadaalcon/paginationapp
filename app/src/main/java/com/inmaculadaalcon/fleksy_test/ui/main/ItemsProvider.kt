package com.inmaculadaalcon.fleksy_test.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object ItemsProvider {
  private val _observable = MutableLiveData<List<TopRatedTVShow>>()
  val observable: LiveData<List<TopRatedTVShow>> get() = _observable

  private var values = emptyList<TopRatedTVShow>()

  fun startEmitting() {
  GlobalScope.  launch(Dispatchers.Main) {
      while (true) {
        delay(1000)
        _observable.value = values
      }
    }
  }

}
