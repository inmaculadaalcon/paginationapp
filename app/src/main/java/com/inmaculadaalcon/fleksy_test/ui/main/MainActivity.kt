package com.inmaculadaalcon.fleksy_test.ui.main

import android.os.Bundle

import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import arrow.core.left
import arrow.core.right
import com.inmaculadaalcon.fleksy_test.databinding.ActivityMainBinding
import com.inmaculadaalcon.fleksy_test.ui.adapter.TopRatedTVShowAdapter
import com.inmaculadaalcon.fleksy_test.ui.base.BaseActivity
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

    viewModel.getTopRatedTV(1)

    binding.recyclerview.adapter = TopRatedTVShowAdapter()

    lifecycleScope.launch{
      launch {
        viewModel.getTopRatedTV(1)
        }
      }
    }

   /* ItemsProvider.observable.observe(this, Observer {
     // adapter.items = viewModel.getTopRatedTV(1)
    })*/
}
