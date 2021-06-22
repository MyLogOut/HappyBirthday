package com.sirstudios.happybirthday.carrouselAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sirstudios.happybirthday.Form
import com.sirstudios.happybirthday.databinding.BackgroundPickerItemBinding


class BackgroundPickerAdapter(
    private val backgroundId: List<Int> = listOf(),
    private val activity: Form
) : RecyclerView.Adapter<BackgroundCarrouselVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundCarrouselVH {
        val itemBinding = BackgroundPickerItemBinding
            .inflate(
                LayoutInflater
                    .from(parent.context),
                parent,
                false
            )
        return BackgroundCarrouselVH(itemBinding)
    }

    override fun onBindViewHolder(holder: BackgroundCarrouselVH, position: Int) {
        val id = backgroundId[position]
        val drawable = ContextCompat.getDrawable(activity, id)
        holder.backgroundHolder.background = drawable
        holder.backgroundHolder.tag = id.toString()
        holder.bind(backgroundId[position], clickListener = activity)
    }

    override fun getItemCount(): Int {
        return backgroundId.size
    }
}