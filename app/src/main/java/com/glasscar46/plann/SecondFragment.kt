package com.glasscar46.plann

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.glasscar46.plann.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
const val MINUTESPERHOUR = 24
class SecondFragment : Fragment() {
    private val viewModel : EventViewModel by activityViewModels()
    private  lateinit var binding : FragmentSecondBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        binding.secondFragment = this
        viewModel.eventType.observe(viewLifecycleOwner, { if(it == "Daily"){
            binding.dateSelectButton.isClickable = false
        }
        })
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(adapter: AdapterView<*>?, view: View?, position: Int, id: Long) {
               viewModel.updateEventType(adapter?.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

        }
    }
    fun showDateDialog(){
        val dialog = MaterialDialog(requireContext())
                .noAutoDismiss()
                .customView(R.layout.dialog_date)
        dialog.findViewById<Button>(R.id.ok_button).setOnClickListener {
           with(dialog.findViewById<DatePicker>(R.id.date_picker)){
             viewModel.updateEventDay(getString(R.string.DayFormat,dayOfMonth,month,year))
               dialog.dismiss()
           }
        }
        dialog.show()
    }
    /** shows a dialog**/
    fun showTimeDialog(){
        val dialog = MaterialDialog(requireContext())
                .noAutoDismiss()
                .customView(R.layout.dialog_time_duration)

        var time : String
        dialog.findViewById<Button>(R.id.save_button).setOnClickListener{
           if(!dialog.findViewById<EditText>(R.id.duration_edit).text.isNullOrEmpty()) {
               val checked = dialog.findViewById<RadioGroup>(R.id.radio_group).checkedRadioButtonId
            with ( dialog.findViewById<TimePicker>(R.id.time_picker)){
                time = getString(R.string.eventTime,hour.toString(),minute.toString())
            }
            val durationString = (dialog.findViewById<EditText>(R.id.duration_edit).text.toString())
            val duration : Int
           if(checked == R.id.minutes){
                duration = durationString.toInt() * MINUTESPERHOUR
           }
            else{
                duration = durationString.toInt()
           }
            viewModel.saveEventTime(time,duration)
               binding.durationSelectButton.text = time + "-" + durationString
            dialog.dismiss()
           }
        }
        dialog.show()
    }
}