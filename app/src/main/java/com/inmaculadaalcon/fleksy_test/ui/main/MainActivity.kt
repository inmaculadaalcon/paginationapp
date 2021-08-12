package com.inmaculadaalcon.fleksy_test.ui.main

import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import arrow.core.left
import arrow.core.right
import com.inmaculadaalcon.fleksy_test.databinding.ActivityMainBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.ui.adapter.TopRatedTVShowAdapter
import com.inmaculadaalcon.fleksy_test.ui.base.BaseActivity
import com.inmaculadaalcon.fleksy_test.ui.base.ScreenState
import com.inmaculadaalcon.fleksy_test.ui.base.TopRatedTVShowsStateScreen
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class MainActivity: BaseActivity() {

  private val viewModel: MainViewModel by inject()

  private val adapter = TopRatedTVShowAdapter()
  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.recyclerview.adapter = TopRatedTVShowAdapter()

    lifecycleScope.launch {
      println("RUINA -> Lifecycle has been launched")
      viewModel.screenState.collect { screenState ->
        println("RUINA -> ScreenState: $screenState")
        when (screenState) {
          is ScreenState.Loading -> println("RUINA Loading")
          is ScreenState.Render<TopRatedTVShowsStateScreen> -> handleRenderState(screenState.renderState)
          is ScreenState.Error -> Unit
          is ScreenState.EmptyData -> Unit
        }
      }
    }
    viewModel.getTopRatedTV(1)


    /* ItemsProvider.observable.observe(this, Observer {
    // adapter.items = viewModel.getTopRatedTV(1)
   })*/
  }
  fun handleRenderState(renderState: TopRatedTVShowsStateScreen){
    when(renderState) {
      is TopRatedTVShowsStateScreen ->
        println("TopRatedTVShow")

    }
  }
}
