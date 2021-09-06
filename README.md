Pagination Android App
================================= 

** This is the code for investigating some news ways for implementing pagination in Android

# Description

The Pagination App is an application which will show an endless list of top rated tv shows and if 
We click over any of the item list It will appear the detail of the show and also if we swipe to 
left or right It will show the similar tv shows and also in each similar tvshow we can see another
list at bottom with similar to it.
All data has been got from [TheMovieDb.Org](https://themoviedb.org) public api 

# Features
The app displays a list of tv shows. Users can see details about them and also navigate between similar
tv shows
![](gif2.gif)  

![](gif1.gif)

![](gif3.gif)


# Development Environment
This app is written entirely in Kotlin and uses the Gradle build system
Asynchronous tasks are handled with coroutines. 
Coroutines allow for simple and safe management of one-shot operations as well as building and consuming streams of data using Kotlin Flows.

### Libraries

- Application entirely written in [Kotlin](https://kotlinlang.org)
- Asynchronous processing using [Coroutines](https://kotlin.github.io/kotlinx.coroutines/)
- Uses [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
- [Glide][glide] for image loading
- Uses [Paging3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) which one has the following
features and I found as the best solution for the problem indicated.
  - In-memory caching for your paged data. This ensures that your app uses system resources efficiently while working with paged data.
  - Built-in request deduplication, ensuring that your app uses network bandwidth and system resources efficiently.
  - Configurable RecyclerView adapters that automatically request data as the user scrolls toward the end of the loaded data.
  - First-class support for Kotlin coroutines and Flow, as well as LiveData and RxJava. 
  - Built-in support for error handling, including refresh and retry capabilities.
    
### API keys

You need to supply API / client keys for the service the app uses.

- [TMDb](https://developers.themoviedb.org)

Once you obtain the key, you can set them in your `~/local.properties`:

### Architecture
The architecture is built around Android Architecture Components and follows the recommendations laid out in the Guide to App Architecture.
Logic is kept away from Activities and moved to ViewModels. 
Data is observed using Kotlin Flows and the Data Binding Library binds UI components in layouts to the app's data sources.

The [Jetpack Benchmark library](https://developer.android.com/studio/profile/benchmark) makes it easy to benchmark your code from within Android Studio.
The library handles warmup, measures your code performance, and outputs benchmarking results to the Android Studio console. 
I try to add a few benchmark tests also


Dependency Injection is implemented with [Koin](https://insert-koin.io/). I prefer to use this di library because is the last one I used in my current project.
But It is easy to change to Hilt for example.

Inmaculada Alcón Piñero