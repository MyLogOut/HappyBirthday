package com.sirstudios.happybirthday

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.sirstudios.happybirthday.carrouselAdapter.BackgroundPickerAdapter
import com.sirstudios.happybirthday.carrouselAdapter.ClickListener
import com.sirstudios.happybirthday.data.DataProvider
import com.sirstudios.happybirthday.extensions.*


class Form : AppCompatActivity(), ClickListener<Int> {
    private lateinit var data: List<Int>
    private lateinit var horizontalLayoutManager: LinearLayoutManager
    private lateinit var rvBackgroundPicker: RecyclerView //RecyclerView Background Picker
    private lateinit var adapter: BackgroundPickerAdapter

    private var backgroundId: Pair<Int, Int> = (-1 to -1) //First = clicked one. Second = the focused one.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val editTextRecipient: EditText = findViewById(R.id.et_Recipient)
        val editTextSender: EditText = findViewById(R.id.et_Sender)
        val buttonGenerate: Button = findViewById(R.id.btn_generate)
        val previousButton: MaterialButton = findViewById(R.id.btn_previousBackground)
        val nextButton: MaterialButton = findViewById(R.id.btn_nextBackground)

        /*Instantiating Recyclerview 's LinearLayoutManager and itself.*/
        horizontalLayoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvBackgroundPicker = findViewById(R.id.rv_backgroundPicker)
        data = DataProvider.backgrounds
        rvBackgroundPicker.layoutManager = horizontalLayoutManager
        rvBackgroundPicker.adapter = BackgroundPickerAdapter(data, this)
        adapter = rvBackgroundPicker.adapter as BackgroundPickerAdapter


        previousButton.setOnClickListener {
            //Get the previousItem's position
            rvBackgroundPicker.previousItem()
            rvBackgroundPicker.focusCurrentChild(false)
            //Smoothly scrolls to the previousItem's position
            //editTextSender.setText(handleChosenBackgroundId(backgroundId))
        }

        nextButton.setOnClickListener {
            rvBackgroundPicker.nextItem()
            rvBackgroundPicker.focusCurrentChild(true)
            //editTextRecipient.setText(handleChosenBackgroundId(backgroundId))
        }

        buttonGenerate.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("recipient", "${editTextRecipient.text}")
            intent.putExtra("sender", "${editTextSender.text}")
            intent.putExtra(
                "background",
                handleChosenBackgroundId(backgroundId)
            ) //Chosen item in RecyclerView
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "onResume(): backgroundId = $backgroundId", Toast.LENGTH_SHORT).show()
    }

    override fun onPause() {
        super.onPause()
        backgroundId = (-1 to backgroundId.second)
    }

    override fun itemClicked(item: Int) {
        Toast.makeText(this, "Item [$item] clicked!", Toast.LENGTH_SHORT).show()
        rvBackgroundPicker.smoothScrollToViewByTag(item)
        backgroundId = (item to backgroundId.second)
    }

    private fun handleChosenBackgroundId(id: Pair<Int, Int>): Int {
        return if (id.first >= 0)
            id.first
        else {
            //val holder = rvBackgroundPicker.getCurrentItemId()
            //Toast.makeText(this, "idValue: $id currentItemId: $holder", Toast.LENGTH_SHORT).show()
            //holder
            rvBackgroundPicker.getCurrentItemId()
        }
    }

}
