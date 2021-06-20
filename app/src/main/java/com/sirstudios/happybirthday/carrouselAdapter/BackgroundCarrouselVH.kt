package com.sirstudios.happybirthday.carrouselAdapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sirstudios.happybirthday.R

class BackgroundCarrouselVH(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val backgroundHolder: ImageView = itemView.findViewById(R.id.iv_backgroundPickerItem)

    fun bind(backgroundId: Int, clickListener: ClickListener<Int>) {
        clickListener.let {
            itemView.setOnClickListener {
                clickListener.itemClicked(backgroundId)
            }
        }
    }
}