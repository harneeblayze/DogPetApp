package com.example.dogpetapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.dogpetapp.R
import com.example.dogpetapp.viewmodel.DogDetailViewModel
import kotlinx.android.synthetic.main.fragment_dog_detail.*


class DogDetailFragment : Fragment() {
    val viewModel:DogDetailViewModel by lazy {
        ViewModelProviders.of(this).get(DogDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       /* listbtn.setOnClickListener {
            val action:NavDirections = DogDetailFragmentDirections.actionDogDetailFragmentToDogListFragment()
            Navigation.findNavController(it).navigate(action)
        }*/
        viewModel.fetchData()
        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.dogItem.observe(this, Observer { dog ->
            dog?.let {
                dogdetailbreed.text = dog.dogBreed
                dogdetailtemprament.text = dog.temperament
                dogdetailgroup.text = dog.lifeSpan
            }
        })


    }
}