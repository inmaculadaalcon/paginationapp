package com.inmaculadaalcon.fleksy_test.ui.base

import com.squareup.moshi.Json

data class ResponseItems<T>(
    @field:Json(name = "results") val results: List<T>
)