package com.glasscar46.plann

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.glasscar46.plann.databinding.EventListItemBinding
import com.glasscar46.plann.databinding.FragmentFirstBinding

class EventAdapter(context: Context, viewModel: EventViewModel) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {
    private  lateinit var binding: EventListItemBinding
    var eventList = viewModel.eventList.value
    val context = context
    class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val imageView = itemView.findViewById<ImageView>(R.id.event_icon)
            val eventName = itemView.findViewById<TextView>(R.id.event_name)
            val eventTime = itemView.findViewById<TextView>(R.id.event_time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
       val inflater = LayoutInflater.from(parent.context)
        binding = EventListItemBinding.inflate(inflater,parent,false)
        return EventViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        with(holder){
            eventName.text = eventList?.get(position)?.eventName.toString()
            eventTime.text = context.getString(R.string.eventTime,eventList?.get(position)?.startTime,eventList?.get(position)?.endTime)
            imageView.setImageResource(R.drawable.ic_event_24)
        }

    }

    override fun getItemCount(): Int {
        return eventList!!.size
    }
}