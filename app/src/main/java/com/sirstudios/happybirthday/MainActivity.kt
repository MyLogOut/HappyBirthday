package com.sirstudios.happybirthday

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.graphics.createBitmap
import com.google.android.material.imageview.ShapeableImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle: Bundle = intent.extras ?: Bundle()
        val recipient = bundle.getString("recipient")
        val sender = bundle.getString("sender")
        val backgroundId: Int = bundle.getInt("background")

        val tvRecipient: TextView = findViewById(R.id.tv_happyBirthdayTo)
        val tvSender: TextView = findViewById(R.id.tv_senderName)
        val sivBackground: ShapeableImageView = findViewById(R.id.siv_cardBackground)

        tvRecipient.text = getString(R.string.happy_birthday, recipient)
        tvSender.text = getString(R.string.sender_name, sender)
        sivBackground.setImageResource(backgroundId)
    }
}