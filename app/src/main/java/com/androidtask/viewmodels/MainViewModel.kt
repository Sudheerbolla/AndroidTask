package com.androidtask.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.androidtask.repo.MainRepository
import com.androidtask.models.EarthquakesRootModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel constructor(private val repository: MainRepository) : ViewModel() {

    val earthquakeList = MutableLiveData<EarthquakesRootModel>()
    val errorMessage = MutableLiveData<String>()

    fun getAllEarthquakes() {

        val response = repository.getAllEarthquakes()
        response.enqueue(object : Callback<EarthquakesRootModel> {
            override fun onResponse(
                call: Call<EarthquakesRootModel>,
                response: Response<EarthquakesRootModel>
            ) {
                earthquakeList.postValue(response.body())
            }

            override fun onFailure(call: Call<EarthquakesRootModel>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}