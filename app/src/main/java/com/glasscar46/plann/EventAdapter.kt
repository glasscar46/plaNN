package com.glasscar46.plann

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glasscar46.plann.databinding.EventListItemBinding


class EventAdapter(context: Context, list: List<Event>) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    private  lateinit var binding: EventListItemBinding
    private var eventList = list
    val context = context
    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView: ImageView = itemView.findViewById(R.id.event_icon)
            val eventName: TextView = itemView.findViewById(R.id.event_name)
            val eventTime: TextView = itemView.findViewById(R.id.event_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        binding = EventListItemBinding.inflate(inflater,parent,false)
        return EventViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        with(holder){
            eventName.text = eventList[position].eventName
            eventTime.text = context.getString(R.string.eventTime, eventList[position].startTime, eventList[position].endTime)
            imageView.setImageResource(R.drawable.ic_event_24)
        }

    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}