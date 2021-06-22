package com.sirstudios.happybirthday.carrouselAdapter

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.sirstudios.happybirthday.databinding.BackgroundPickerItemBinding

class BackgroundCarrouselVH(itemView: BackgroundPickerItemBinding) : RecyclerView.ViewHolder(itemView.root) {

    val backgroundHolder: ImageView = itemView.ivBackgroundPickerItem

    fun bind(backgroundId: Int, clickListener: ClickListener<Int>) {
        clickListener.let {
            itemView.setOnClickListener {
                clickListener.itemClicked(backgroundId)
            }
        }
    }
}