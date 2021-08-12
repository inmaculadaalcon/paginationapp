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
  const val retrofit = "2.9.0"
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
  const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"
  const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
}

object GlideDependencies {
  const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
  const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object SquareDependencies {
  const val retrofit =  "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
  const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
  const val okhttp = "com.squareup.okhttp3:okhttp:4.9.1"
}
