package com.inmaculadaalcon.fleksy_test.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.inmaculadaalcon.fleksy_test.BuildConfig
import com.inmaculadaalcon.fleksy_test.R
import com.inmaculadaalcon.fleksy_test.databinding.ActivityMainBinding
import com.inmaculadaalcon.fleksy_test.databinding.DetailTvshowActivityBinding
import com.inmaculadaalcon.fleksy_test.domain.model.DetailTVShow
import com.inmaculadaalcon.fleksy_test.ui.base.BaseActivity
import com.inmaculadaalcon.fleksy_test.ui.main.TopRatedTVShowsListViewModel
import kotlinx.android.synthetic.main.detail_tvshow_activity.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DetailTVShowActivity: BaseActivity<DetailTvshowActivityBinding>() {

    companion object {
        const val BACKGROUND_COLOR = "backgroundcolor"
        const val TV_SHOW_ID = "tvshowId"
    }

    private val viewModel: DetailTVShowViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val backgroundColor = intent.getIntExtra(BACKGROUND_COLOR, R.drawable.background_blue)
        val tvShowId = intent.getIntExtra(TV_SHOW_ID, 0)
        binding.root.background = ContextCompat.getDrawable(this, backgroundColor)

        lifecycleScope.launch {
            viewModel.getDetailTVShow(tvShowId)
        }
        lifecycleScope.launchWhenStarted {
            viewModel.screenState.collect { it ->
                if (it != null){
                    drawDetails(it.data as DetailTVShow)
                }
            }
        }
    }

    override val bindingInflater: (LayoutInflater) -> DetailTvshowActivityBinding
        get() = DetailTvshowActivityBinding::inflate

    private fun drawDetails(details: DetailTVShow) {
        binding.title.text = details.name
        binding.overviewText.text = details.overview
        Glide.with(this).load(BuildConfig.IMAGE_BASE_URL+details.backdropPath).into(binding.imagePoster)
    }

}