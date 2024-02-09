package com.example.luckynumapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class LuckyNumberActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lucky_number)

        val luckyText: TextView = findViewById(R.id.luckyNumText)
        val shareBtn: Button = findViewById(R.id.shareBtn)

        var user_name = receiveUserName()

        // Uncomment the Toast statement to display the username
        // Toast.makeText(this, "" + user_name, Toast.LENGTH_LONG).show()

        var num = generateRandomNumber()

        luckyText.text = num.toString() // Convert num to String before setting it

        shareBtn.setOnClickListener() {
            shareData(user_name, num)
        }
    }

    private fun receiveUserName(): String? {
        val bundle: Bundle? = intent.extras
        return bundle?.getString("name")
    }

    private fun generateRandomNumber(): Int {
        return Random.nextInt(1000)
    }

    private fun shareData(username: String?, num: Int) {
        val i = Intent(Intent.ACTION_SEND)
        i.type = "text/plain"

        val message = if (username != null) {
            "$username is Lucky Today. His Lucky num is $num"
        } else {
            "Lucky num is $num"
        }

        i.putExtra(Intent.EXTRA_TEXT, message)

        // Check if there is an app that can handle the Intent
        if (i.resolveActivity(packageManager) != null) {
            startActivity(Intent.createChooser(i, "Share using"))
        } else {
            Toast.makeText(this, "No app can handle this action", Toast.LENGTH_SHORT).show()
        }
    }

}
