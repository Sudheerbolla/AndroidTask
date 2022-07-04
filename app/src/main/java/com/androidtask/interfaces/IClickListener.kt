package com.androidtask.interfaces

import android.view.View
import com.androidtask.models.EarthquakesRowModel

interface IClickListener {
    fun onClick(view: View, itemAtPosition: EarthquakesRowModel)
}