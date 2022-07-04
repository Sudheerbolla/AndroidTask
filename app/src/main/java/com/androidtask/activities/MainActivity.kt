package com.androidtask.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidtask.MainAdapter
import com.androidtask.R
import com.androidtask.databinding.ActivityMainBinding
import com.androidtask.interfaces.IClickListener
import com.androidtask.interfaces.wsutils.RetrofitService
import com.androidtask.models.EarthquakesRowModel
import com.androidtask.repo.MainRepository
import com.androidtask.viewmodels.MainViewModel
import com.androidtask.viewmodels.MyViewModelFactory

class MainActivity : AppCompatActivity(), IClickListener {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initComponents()
    }

    private fun initComponents() {
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(MainRepository(retrofitService))
        ).get(MainViewModel::class.java)
        binding.rvEarthquakes.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
        binding.rvEarthquakes.adapter = adapter

        viewModel.earthquakeList.observe(this, Observer {
            adapter.setList(it.earthquakes)
        })

        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
        viewModel.getAllEarthquakes()
    }

    override fun onClick(view: View, itemAtPosition: EarthquakesRowModel) {
        val intent = Intent(this, MapsActivity::class.java)
        intent.putExtra("selectedItem", itemAtPosition)
        startActivity(intent)
    }

}