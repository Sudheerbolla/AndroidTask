package com.androidtask.repo

import com.androidtask.interfaces.wsutils.RetrofitService

class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getAllEarthquakes() = retrofitService.getAllEarthquakes()
}