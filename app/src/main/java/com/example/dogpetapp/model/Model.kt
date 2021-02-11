package com.example.dogpetapp.model

import com.google.gson.annotations.SerializedName

data class DogBreed(
    @SerializedName("id")
    val breedId:String?,
    @SerializedName("name")
    val dogBreed:String?,
    val life_span:String?,
    val breed_group:String?,
    val bred_for:String?,
    val temperament:String?,
    @SerializedName("url")
    val imageUrl:String?
)