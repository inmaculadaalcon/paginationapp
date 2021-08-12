object Versions {
  const val appcompat = "1.3.1"
  const val core_ktx = "1.6.0"
  const val espresso = "3.4.0"
  const val glide = "4.12.0"


  const val junit_android_test = "1.1.3"
  const val junit_test_implementation = "4.13.2"
  const val koin = "3.1.2"
  const val material = "1.4.0"
  const val moshi = "1.12.0"
  const val okhttp = "4.9.1"
  const val retrofit = "2.9.0"
  const val retrofit_conv_moshi = "2.4.0"

  const val arrow = "0.10.5"

  object Coroutines {
    const val CORE = "1.4.2"
    const val ANDROID = "1.4.2"
  }
}

object DefaultConfigValues {
  const val BUILD_TOOLS = "30.0.2"
  const val APPLICATION_ID = "com.inmaculadaalcon.fleksy_test"
  const val COMPILE_SDK = 31
  const val MIN_SDK = 21
  const val TARGET_SDK = 31

  const val VERSION_CODE = 1
  const val VERSION_NAME = "1.0"
}

object BuildTypes {
  const val DEBUG = "debug"
  const val RELEASE = "release"
}

object AndroidxDependencies {
  const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
  const val corektx = "androidx.core:core-ktx:${Versions.core_ktx}"
}

object MaterialDependencies {
  const val material = "com.google.android.material:material:${Versions.material}"
}

object TestDependencies {
  const val junitTestImplementation = "junit:junit:${Versions.junit_test_implementation}"
  const val junitAndroidTestImplementation = "androidx.test.ext:junit:${Versions.junit_android_test}"
  const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}

object KoinDependencies {
  const val koinCore ="io.insert-koin:koin-android:${Versions.koin}"
  const val koinWorkManager ="io.insert-koin:koin-androidx-workmanager:${Versions.koin}"
  const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
}

object GlideDependencies {
  const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
  const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object SquareDependencies {
  const val retrofit =  "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val retrofit_converter_moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit_conv_moshi}"
  const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
  const val moshi_codegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
  const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
  const val okhttp_loggin_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
}

object ArrowDependencies {
  const val arrow_core = "io.arrow-kt:arrow-core-data:${Versions.arrow}"
  const val arrow_fx = "io.arrow-kt:arrow-fx:${Versions.arrow}"
  const val arrow_fx_coroutines = "io.arrow-kt:arrow-fx-kotlinx-coroutines:${Versions.arrow}"
}

object CoroutinesDependencies {
  const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Coroutines.CORE}"
  const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutines.ANDROID}"
}
