package com.androidtask

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.androidtask.databinding.AdapterEarthqaukeBinding
import com.androidtask.interfaces.IClickListener
import com.androidtask.models.EarthquakesRowModel

class MainAdapter(val onIClickListener: IClickListener) : RecyclerView.Adapter<MainViewHolder>() {

    var earthquakes = mutableListOf<EarthquakesRowModel>()

    fun setList(earthquakes: List<EarthquakesRowModel>) {
        this.earthquakes = earthquakes.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterEarthqaukeBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val earthquake = earthquakes[position]
        holder.binding.apply {
            tvDepth.text = "Depth : ${earthquake.depth}"
            tvMagnitude.text = "Magnitude : ${earthquake.magnitude}"
            tvLat.text = "Lat : ${earthquake.lat}"
            tvLong.text = "Lng : ${earthquake.lng}"

            layRoot.setBackgroundColor(
                if (earthquake.magnitude!! >= 8.0)
                    Color.RED else Color.YELLOW
            )

            layRoot.setOnClickListener {
                onIClickListener.onClick(it, earthquake)
            }
        }
    }

    override fun getItemCount(): Int {
        return earthquakes.size
    }

}

class MainViewHolder(val binding: AdapterEarthqaukeBinding) :
    RecyclerView.ViewHolder(binding.root)