package com.inmaculadaalcon.fleksy_test.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import arrow.core.right
import arrow.core.rightIfNotNull
import com.inmaculadaalcon.fleksy_test.databinding.ActivityMainBinding
import com.inmaculadaalcon.fleksy_test.domain.model.TopRatedTVShow
import com.inmaculadaalcon.fleksy_test.ui.adapter.TopRatedTVShowAdapter
import com.inmaculadaalcon.fleksy_test.ui.base.BaseActivity
import kotlinx.coroutines.flow.collect

import org.koin.core.component.inject

class MainActivity() : BaseActivity<ActivityMainBinding>() {


  override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
    get() = ActivityMainBinding::inflate

  private val viewModel: MainViewModel by inject()

  private val adapter = TopRatedTVShowAdapter()


  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(binding.root)

    binding.recyclerview.setHasFixedSize(true)
    binding.recyclerview.adapter = adapter
    binding.recyclerview.layoutManager = LinearLayoutManager(this)

    lifecycleScope.launchWhenStarted {
      viewModel.screenState.collect {
       val data =  it?.data
        if(data!=null) {
          adapter.items = data.results
        }
      }
    }
    viewModel.getTopRatedTV(1)

   /* binding.recyclerview.addOnScrollListener(PaginationScrollListener(linearLayoutManager){

    })*/
  }

}
