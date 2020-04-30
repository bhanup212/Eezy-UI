package com.bhanupro.eezyui.ui.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bhanupro.eezyui.R
import com.bhanupro.eezyui.databinding.WeekDaysRowLayoutBinding
import com.bhanupro.eezyui.model.Week
import com.bhanupro.eezyui.ui.DoublePlanFragment
import kotlinx.android.synthetic.main.week_days_row_layout.view.*


/**
 * Created by Bhanu Prakash Pasupula on 14,Apr-2020.
 * Email: pasupula1995@gmail.com
 */
class WeeksAdapter(private val context:Context,private val list:ArrayList<Week>):RecyclerView.Adapter<WeeksAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeeksAdapter.ViewHolder {
        val view = WeekDaysRowLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: WeeksAdapter.ViewHolder, position: Int) {
        val week = list[position]
        holder.bindData(week,position)

        if (week.isSelected || DoublePlanFragment.selectedWeekPosition == position){
            holder.rowView.dateTv.background = ContextCompat.getDrawable(context,R.drawable.week_selected_bg)
            holder.rowView.dateTv.setTextColor(Color.WHITE)
        }else{
            holder.rowView.dateTv.background = ContextCompat.getDrawable(context,R.drawable.week_unseleted_bg)
            holder.rowView.dateTv.setTextColor(Color.BLACK)
        }
        holder.rowView.dateTv.setOnClickListener {
            resetSelected()
            week.isSelected = true
            DoublePlanFragment.selectedWeekPosition = position
            notifyItemChanged(position)
        }

    }
    private fun resetSelected(){
        list.forEachIndexed { index, week ->
            if (week.isSelected){
                week.isSelected = false
                notifyItemChanged(index)
                return@forEachIndexed
            }
        }
    }

    inner class ViewHolder(val rowView:WeekDaysRowLayoutBinding):RecyclerView.ViewHolder(rowView.root){

        fun bindData(week: Week,position: Int){
            setWeekName(position)
            rowView.dateTv.text = week.dayOfWeek
        }
        private fun setWeekName(position: Int){
            val weekName = when(position){
                0 -> "M"
                1 -> "T"
                2 -> "W"
                3 -> "T"
                4 -> "F"
                5 -> "S"
                else -> "S"
            }
            rowView.weekNameTv.text = weekName
        }
    }
}