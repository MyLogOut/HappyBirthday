package com.sirstudios.happybirthday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.imageview.ShapeableImageView
import com.sirstudios.happybirthday.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var mainViewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainViewBinding.root)

        val bundle: Bundle = intent.extras ?: Bundle()
        val recipient = bundle.getString("recipient")
        val sender = bundle.getString("sender")
        val backgroundId: Int = bundle.getInt("background")

        mainViewBinding.tvHappyBirthdayTo.text = getString(R.string.happy_birthday, recipient)
        mainViewBinding.tvSenderName.text = getString(R.string.sender_name, sender)
        mainViewBinding.sivCardBackground.setImageResource(backgroundId)
    }
}