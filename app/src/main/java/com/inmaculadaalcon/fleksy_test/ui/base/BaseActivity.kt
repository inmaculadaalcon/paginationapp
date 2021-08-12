package com.inmaculadaalcon.fleksy_test.ui.base

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import org.koin.core.component.KoinComponent

abstract class BaseActivity: AppCompatActivity(), KoinComponent{

  //This method is for adapting the UI for all font scales
  override fun attachBaseContext(newBase: Context) {
    val newConfigFont = Configuration(newBase.resources.configuration)
    newConfigFont.fontScale = 1.0f
    applyOverrideConfiguration(newConfigFont)
    super.attachBaseContext(newBase)
  }
}
