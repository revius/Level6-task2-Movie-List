package com.example.movielist

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MoviesApi {
    companion object {
        // The base url off the api.
        private const val baseUrl = "https://api.themoviedb.org/"

        /**
         * @return [MoviesApiService] The service class off the retrofit client.
         */
        fun createApi(): MoviesApiService {
            // Create an OkHttpClient to be able to make a log of the network traffic
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()

            // Create the Retrofit instance
            val moviesApi = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            // Return the Retrofit MoviesApiService
            return moviesApi.create(MoviesApiService::class.java)
        }
    }
}
