package com.inmaculadaalcon.pagination.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import org.koin.core.component.KoinComponent

abstract class BaseActivity<T: ViewBinding>: AppCompatActivity(), KoinComponent{

  private var _binding: ViewBinding? = null
  abstract val bindingInflater: (LayoutInflater) -> T

  protected val binding: T
    get() = _binding as T

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    _binding = bindingInflater.invoke(layoutInflater)
    setContentView(requireNotNull(_binding).root)
  }

  override fun onDestroy() {
    super.onDestroy()
    _binding = null
  }
}
