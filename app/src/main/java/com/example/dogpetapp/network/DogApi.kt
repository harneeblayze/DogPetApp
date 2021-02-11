package com.example.dogpetapp.network

import com.example.dogpetapp.model.DogBreed
import io.reactivex.Single
import retrofit2.http.GET

interface DogApi {
    @GET("/DevTides/DogsApi/master/dogs.json")
    fun getDogs(): Single<List<DogBreed>>
}