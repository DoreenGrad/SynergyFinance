package com.example.synergyfinance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class PasswordActivity : AppCompatActivity() {
    var username: EditText? = null
    var reset: Button? = null
    var dB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        username = findViewById<View>(R.id.username_reset) as EditText
        reset = findViewById<View>(R.id.btnReset) as Button
        dB = DBHelper(this)
        reset!!.setOnClickListener {
            val user = username!!.text.toString()
            val checkUser = dB!!.checkUsername(user)
            if (checkUser == true) {
                val intent = Intent(applicationContext, ResetActivity::class.java)
                intent.putExtra("username", user)
                startActivity(intent)
            } else {
                Toast.makeText(this@PasswordActivity, "User does not exist", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}