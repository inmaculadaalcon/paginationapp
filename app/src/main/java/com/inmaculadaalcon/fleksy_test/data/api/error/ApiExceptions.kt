package com.inmaculadaalcon.fleksy_test.data.api.error

abstract class ServerApiException : Exception()

object ServerConnectionApiException : ServerApiException()

object ServerUnknownApiException : ServerApiException()

object Server500ApiException : ServerApiException()

object Server404ApiException : ServerApiException()

object Server403ApiException : ServerApiException()
