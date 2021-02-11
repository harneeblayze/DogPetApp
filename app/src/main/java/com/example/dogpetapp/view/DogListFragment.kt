package com.example.dogpetapp.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dogpetapp.R
import com.example.dogpetapp.view.adapters.DogListAdapter
import com.example.dogpetapp.view.adapters.DogListener
import com.example.dogpetapp.viewmodel.ListViewModel
import kotlinx.android.synthetic.main.fragment_dog_list.*


class DogListFragment : Fragment(), DogListener {
    val viewModel:ListViewModel by lazy { ViewModelProviders.of(this).get(ListViewModel::class.java) }
    private var dogList_adapter:DogListAdapter = DogListAdapter(arrayListOf(), this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dog_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refresh()
        dogsList_rv.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogList_adapter
        }

        refresh.setOnRefreshListener {
            dogsList_rv.visibility = View.GONE
            listError.visibility = View.GONE
            loader.visibility = View.VISIBLE
            viewModel.refresh()
            refresh.isRefreshing = false
        }

        observedData()


        /*detailsbtn.setOnClickListener {
            val action: NavDirections = DogListFragmentDirections.actionDogListFragmentToDogDetailFragment()
            Navigation.findNavController(it).navigate(action)
        }*/
    }

    fun observedData(){
        viewModel.dogList.observe(this, Observer { dogbreeds ->
            dogbreeds?.let {
                dogsList_rv.visibility = View.VISIBLE
                dogList_adapter.setDogData(dogbreeds)
            }

        })

        viewModel.loader.observe(this, Observer { isloading ->
            isloading?.let {
                loader.visibility = if(it)View.VISIBLE else View.GONE
                if (it){
                    listError.visibility = View.GONE
                    dogsList_rv.visibility = View.GONE
                }
            }
        })

        viewModel.error.observe(this, Observer { isError ->
            isError?.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

    }

    override fun onDogItemClick(position: Int) {
        val action: NavDirections = DogListFragmentDirections.actionDogListFragmentToDogDetailFragment()
        view?.let { Navigation.findNavController(it).navigate(action) }


    }
}