package com.example.dogpetapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogpetapp.model.DogBreed

class DogDetailViewModel:ViewModel() {
   private val _dogItem = MutableLiveData<DogBreed>()

    val dogItem:LiveData<DogBreed>
        get() = _dogItem


    fun fetchData(){
        val dog1 = DogBreed("1","German Shepherd","10years","breedGroup", "bredFor","Temperament Wild","")
        _dogItem.value = dog1
    }
}