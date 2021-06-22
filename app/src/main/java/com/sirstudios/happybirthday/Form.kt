package com.sirstudios.happybirthday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.sirstudios.happybirthday.carrouselAdapter.BackgroundPickerAdapter
import com.sirstudios.happybirthday.carrouselAdapter.ClickListener
import com.sirstudios.happybirthday.data.DataProvider
import com.sirstudios.happybirthday.databinding.ActivityFormBinding
import com.sirstudios.happybirthday.extensions.*


class Form : AppCompatActivity(), ClickListener<Int> {

    private lateinit var formViewBinding: ActivityFormBinding
    private lateinit var data: List<Int>
    private lateinit var horizontalLayoutManager: LinearLayoutManager
    //private late-init var rvBackgroundPicker: RecyclerView //RecyclerView Background Picker

    private var backgroundId: Pair<Int, Int> = (-1 to -1) //First = clicked one. Second = the focused one.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        formViewBinding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(formViewBinding.root)


        /*Instantiating Recyclerview 's LinearLayoutManager and itself.*/
        horizontalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        data = DataProvider.backgrounds
        formViewBinding.irvBackgroundCarousel.rvBackgroundPicker.layoutManager = horizontalLayoutManager
        formViewBinding.irvBackgroundCarousel.rvBackgroundPicker.adapter = BackgroundPickerAdapter(data, this)


        formViewBinding.irvBackgroundCarousel.btnPreviousBackground.setOnClickListener {
            //Get the previousItem's position
            formViewBinding.irvBackgroundCarousel.rvBackgroundPicker.previousItem()
            formViewBinding.irvBackgroundCarousel.rvBackgroundPicker.focusCurrentChild(false)
            //Smoothly scrolls to the previousItem's position
            //editTextSender.setText(handleChosenBackgroundId(backgroundId))
        }

        formViewBinding.irvBackgroundCarousel.btnNextBackground.setOnClickListener {
            formViewBinding.irvBackgroundCarousel.rvBackgroundPicker.nextItem()
            formViewBinding.irvBackgroundCarousel.rvBackgroundPicker.focusCurrentChild(true)
            //editTextRecipient.setText(handleChosenBackgroundId(backgroundId))
        }

        formViewBinding.btnGenerate.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("recipient", "${formViewBinding.etRecipient.text}")
            intent.putExtra("sender", "${formViewBinding.etSender.text}")
            intent.putExtra(
                "background",
                handleChosenBackgroundId(backgroundId)
            ) //Chosen item in RecyclerView
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        backgroundId = (-1 to backgroundId.second)
    }

    override fun itemClicked(item: Int) {
        Toast.makeText(this, "Background [$item] clicked!", Toast.LENGTH_SHORT).show()
        formViewBinding.irvBackgroundCarousel.rvBackgroundPicker.smoothScrollToViewByTag(item)
        backgroundId = (item to backgroundId.second)
    }

    private fun handleChosenBackgroundId(id: Pair<Int, Int>): Int {
        return if (id.first >= 0)
            id.first
        else {
            //val holder = rvBackgroundPicker.getCurrentItemId()
            //Toast.makeText(this, "idValue: $id currentItemId: $holder", Toast.LENGTH_SHORT).show()
            //holder
            formViewBinding.irvBackgroundCarousel.rvBackgroundPicker.getCurrentItemId()
        }
    }

}
