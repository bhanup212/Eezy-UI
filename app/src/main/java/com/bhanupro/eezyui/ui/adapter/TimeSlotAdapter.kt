package com.bhanupro.eezyui.ui.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bhanupro.eezyui.R
import com.bhanupro.eezyui.model.TimeSlots
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily


/**
 * Created by Bhanu Prakash Pasupula on 21,Apr-2020.
 * Email: pasupula1995@gmail.com
 */
class TimeSlotAdapter(private val context:Context,private val list:ArrayList<TimeSlots>):RecyclerView.Adapter<TimeSlotAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotAdapter.ViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.time_slot_row_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TimeSlotAdapter.ViewHolder, position: Int) {
        val timeSlot = list[position]

        holder.bindData(timeSlot)

        if (timeSlot.isExpanded){
            holder.collapseCv.isVisible = false
            holder.expandCv.isVisible = true
        }else{
            holder.collapseCv.isVisible = true
            holder.expandCv.isVisible = false
        }

        holder.declineBtn.isVisible = !timeSlot.isAccepted
        if (timeSlot.isAccepted){
            holder.acceptBtn.background = ContextCompat.getDrawable(context,R.drawable.accepted_btn_bg)
            holder.acceptBtn.setTextColor(Color.parseColor("#66c86b"))
        }else{
            holder.acceptBtn.background = ContextCompat.getDrawable(context,R.drawable.accept_btn_bg)
            holder.acceptBtn.setTextColor(Color.WHITE)
        }

        holder.expandImg.setOnClickListener {
            timeSlot.isExpanded = true
            notifyItemChanged(position)
        }
        holder.expandedBlueView.setOnClickListener {
            timeSlot.isExpanded = false
            notifyItemChanged(position)
        }
        holder.acceptBtn.setOnClickListener {
            timeSlot.isAccepted = !timeSlot.isAccepted
            notifyItemChanged(position)
        }

    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val partyImg1: ShapeableImageView = itemView.findViewById(R.id.party_simg)
        private val partyImg2:ShapeableImageView = itemView.findViewById(R.id.party_simg2)
        val collapseCv:MaterialCardView = itemView.findViewById(R.id.afternoon_cv)
        val expandCv:ConstraintLayout = itemView.findViewById(R.id.evening_cl)
        val expandImg:ImageView = itemView.findViewById(R.id.expand_img)
        val acceptBtn: TextView = itemView.findViewById(R.id.accept_btn)
        val declineBtn:MaterialButton = itemView.findViewById(R.id.decline_btn)
        val acceptBtn2:MaterialButton = itemView.findViewById(R.id.accept_btn2)
        private val collapseSlotName:TextView = itemView.findViewById(R.id.afternoon_tv)
        private val expandSlotName:TextView = itemView.findViewById(R.id.evening_tv)
        val expandedBlueView:View = itemView.findViewById(R.id.blue_view)


        init {
            changeImgCorners()
        }

        fun bindData(timeSlots: TimeSlots){
            collapseSlotName.text = timeSlots.slotName
            expandSlotName.text = timeSlots.slotName
        }

        private fun changeImgCorners(){

            val cornerSize = 12f
            partyImg1?.shapeAppearanceModel = partyImg1.shapeAppearanceModel
                .toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED,cornerSize)
                .setTopLeftCorner(CornerFamily.ROUNDED,cornerSize)
                .setBottomLeftCorner(CornerFamily.ROUNDED,cornerSize)
                .setBottomRightCorner(CornerFamily.ROUNDED,cornerSize)
                .build()

            partyImg2?.shapeAppearanceModel = partyImg2.shapeAppearanceModel
                .toBuilder()
                .setTopRightCorner(CornerFamily.ROUNDED,cornerSize)
                .setTopLeftCorner(CornerFamily.ROUNDED,cornerSize)
                .setBottomLeftCorner(CornerFamily.ROUNDED,cornerSize)
                .setBottomRightCorner(CornerFamily.ROUNDED,cornerSize)
                .build()
        }
    }
}