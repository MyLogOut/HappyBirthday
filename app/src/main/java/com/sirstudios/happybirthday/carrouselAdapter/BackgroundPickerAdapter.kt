package com.sirstudios.happybirthday.carrouselAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sirstudios.happybirthday.Form
import com.sirstudios.happybirthday.R


class BackgroundPickerAdapter(
    private val backgroundId: List<Int> = listOf(),
    private val activity: Form
    ): RecyclerView.Adapter<BackgroundCarrouselVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundCarrouselVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.background_picker_item, parent, false)
        return BackgroundCarrouselVH(view)
    }

    override fun onBindViewHolder(holder: BackgroundCarrouselVH, position: Int) {
        val id = backgroundId[position]
        val drawable = ContextCompat.getDrawable(activity, id)

        holder.itemView.background = drawable
        holder.itemView.tag = id.toString()
        holder.bind(backgroundId[position], clickListener = activity)
    }

    override fun getItemCount(): Int {
        return backgroundId.size
    }
}