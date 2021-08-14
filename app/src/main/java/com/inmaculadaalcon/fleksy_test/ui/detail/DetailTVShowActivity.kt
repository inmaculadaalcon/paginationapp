package com.inmaculadaalcon.fleksy_test.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.inmaculadaalcon.fleksy_test.R
import com.inmaculadaalcon.fleksy_test.databinding.ActivityMainBinding
import com.inmaculadaalcon.fleksy_test.databinding.DetailTvshowActivityBinding
import com.inmaculadaalcon.fleksy_test.ui.base.BaseActivity
import org.koin.core.component.KoinComponent

class DetailTVShowActivity: BaseActivity<DetailTvshowActivityBinding>() {

    companion object {
        const val BACKGROUND_COLOR = "backgroundcolor"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val backgroundColor = intent.getIntExtra(BACKGROUND_COLOR, R.drawable.background_blue)
        binding.root.background = ContextCompat.getDrawable(this, backgroundColor)
    }

    override val bindingInflater: (LayoutInflater) -> DetailTvshowActivityBinding
        get() = DetailTvshowActivityBinding::inflate

}