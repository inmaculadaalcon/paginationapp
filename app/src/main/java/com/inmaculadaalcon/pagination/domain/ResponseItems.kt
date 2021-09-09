package com.inmaculadaalcon.pagination.domain

import com.squareup.moshi.Json

data class ResponseItems<T>(
    @field:Json(name = "results") val results: List<T>
)