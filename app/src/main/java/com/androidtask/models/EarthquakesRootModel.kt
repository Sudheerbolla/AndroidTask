package com.androidtask.models

import com.google.gson.annotations.SerializedName

data class EarthquakesRootModel(
    @SerializedName("earthquakes") var earthquakes: ArrayList<EarthquakesRowModel> = arrayListOf()
)