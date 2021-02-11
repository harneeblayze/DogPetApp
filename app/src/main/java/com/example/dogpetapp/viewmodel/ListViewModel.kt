package com.example.dogpetapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dogpetapp.model.DogBreed
import com.example.dogpetapp.network.DogApiService
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel:ViewModel() {
    //use dagger to create this objects later or use hilt
    private val dogsService = DogApiService()
    private val disposable = CompositeDisposable()
    private val _dogList = MutableLiveData<List<DogBreed>>()
    val dogList:LiveData<List<DogBreed>>
        get() = _dogList

    private val _error = MutableLiveData<Boolean>()
    val error:LiveData<Boolean>
        get() = _error

    private val _loader = MutableLiveData<Boolean>()
    val loader:LiveData<Boolean>
    get() = _loader

    //fetch all doglist
    fun refresh(){
       /* val dog1 = DogBreed("1","German Shepherd","10years","breedGroup", "bredFor","","")
        val dog2 = DogBreed("2","Pitbull","15years","breedGroup", "bredFor","","")
        val dog3 = DogBreed("3","Husky","12years","breedGroup", "bredFor","","")

        val dogItems = arrayListOf(dog1,dog2,dog3)
        _dogList.value = dogItems
        _error.value = false
        _loader.value = false*/
        fetchFromRemote()

    }

    fun fetchFromRemote(){
        _loader.value = true

        disposable.add(
            dogsService.getDogs().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>(){
                    override fun onSuccess(t: List<DogBreed>) {
                        _loader.value = false
                        _dogList.value = t
                        _error.value = false
                    }

                    override fun onError(e: Throwable) {
                       _error.value = true
                        _loader.value = false
                        e.printStackTrace()
                    }
                })
        )

    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}