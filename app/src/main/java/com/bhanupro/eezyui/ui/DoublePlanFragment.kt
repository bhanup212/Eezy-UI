package com.bhanupro.eezyui.ui

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.NonNull
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bhanupro.eezyui.MainActivity

import com.bhanupro.eezyui.R
import com.bhanupro.eezyui.model.TimeSlots
import com.bhanupro.eezyui.model.Week
import com.bhanupro.eezyui.ui.adapter.TimeSlotAdapter
import com.bhanupro.eezyui.ui.adapter.WeeksAdapter
import kotlinx.android.synthetic.main.fragment_appbar.*
import kotlinx.android.synthetic.main.fragment_double_plan.*

class DoublePlanFragment : Fragment() {

    private lateinit var weeksPagerAdapter:WeeksPagerAdapter
    private lateinit var timeSlotAdapter: TimeSlotAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, Callback())
    }
    inner class Callback : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_double_plan, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).setSupportActionBar(toolbar)

        setTimeSlots()
        setWeeks()
    }
    private fun setTimeSlots(){
        timeSlotAdapter = TimeSlotAdapter(requireContext(),getTimeSlots())
        time_slot_rv.layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        time_slot_rv.adapter = timeSlotAdapter
    }
    private fun setWeeks(){
        weeksPagerAdapter = WeeksPagerAdapter()
        weeks_viewpager?.adapter = weeksPagerAdapter
    }

    private fun getTimeSlots():ArrayList<TimeSlots>{
        val list = ArrayList<TimeSlots>()
        list.add(TimeSlots("Morning",false))
        list.add(TimeSlots("noon",false))
        list.add(TimeSlots("afternoon",false))
        list.add(TimeSlots("evening",true))
        list.add(TimeSlots("night",false))
        return list
    }

    inner class WeeksPagerAdapter() :
        PagerAdapter() {

        override fun instantiateItem(@NonNull collection: ViewGroup, position: Int): Any {
            val inflater = LayoutInflater.from(requireContext())

            val view = inflater.inflate(R.layout.weeks_rv, null)
            val weeksRv = view.findViewById<RecyclerView>(R.id.weeks_rv_item)
            val adapter = WeeksAdapter(requireContext(), weekList[position])
            weeksRv.adapter = adapter
            (collection as ViewPager).addView(view, 0)

            return view
        }


        override fun destroyItem(@NonNull container: ViewGroup, position: Int, @NonNull view: Any) {
            container.removeView(view as View)
        }

        override fun getItemPosition(`object`: Any): Int {
            return POSITION_NONE
        }

        override fun getCount(): Int = 5

        override fun isViewFromObject(@NonNull view: View, @NonNull `object`: Any): Boolean {
            return view === `object`
        }
    }

    companion object{
        var selectedWeekPosition = -1
        val weekList = ArrayList<ArrayList<Week>>().apply {
            add(ArrayList<Week>().apply {
                add(Week("01",false))
                add(Week("02",false))
                add(Week("03",false))
                add(Week("04",false))
                add(Week("05",false))
                add(Week("06",false))
                add(Week("07",false))
            })
            add(ArrayList<Week>().apply {
                add(Week("08",false))
                add(Week("09",false))
                add(Week("10",false))
                add(Week("11",false))
                add(Week("12",false))
                add(Week("13",false))
                add(Week("14",false))
            })
            add(ArrayList<Week>().apply {
                add(Week("15",false))
                add(Week("16",false))
                add(Week("17",false))
                add(Week("18",false))
                add(Week("19",false))
                add(Week("19",false))
                add(Week("20",false))
            })
            add(ArrayList<Week>().apply {
                add(Week("21",false))
                add(Week("22",false))
                add(Week("23",false))
                add(Week("24",false))
                add(Week("25",false))
                add(Week("26",false))
                add(Week("27",false))
            })
            add(ArrayList<Week>().apply {
                add(Week("28",false))
                add(Week("29",false))
                add(Week("30",false))
                add(Week("31",false))
            })

        }

    }
}
