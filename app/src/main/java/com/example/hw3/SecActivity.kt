package com.example.hw3

import android.app.Activity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class SecActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)

        val tvReceived = findViewById<TextView>(R.id.tvReceived)
        val etReply = findViewById<EditText>(R.id.etReply)
        val btnReturn = findViewById<Button>(R.id.btnReturn)

        val receivedText = intent.getStringExtra("input_key")
        if (!receivedText.isNullOrEmpty()) {
            tvReceived.text = receivedText
        }

        btnReturn.setOnClickListener {
            val replyText = etReply.text.toString()
            val resultIntent = Intent().apply {
                putExtra("reply_key", replyText)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }
    }

}