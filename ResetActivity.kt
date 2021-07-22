package com.example.synergyfinance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class ResetActivity : AppCompatActivity() {
    var username: TextView? = null
    var confirm: Button? = null
    var pass: EditText? = null
    var repass: EditText? = null
    var dB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)

        username = findViewById<View>(R.id.username_reset_text) as TextView
        pass = findViewById<View>(R.id.password_reset) as EditText
        repass = findViewById<View>(R.id.repassword_reset) as EditText
        confirm = findViewById<View>(R.id.btnReset) as Button
        dB = DBHelper(this)
        val intent = intent
        username!!.text = intent.getStringExtra("username")
        confirm!!.setOnClickListener {
            val user = username!!.text.toString()
            val password = pass!!.text.toString()
            val repassword = repass!!.text.toString()
            if (password == repassword) {
                val checkPasswordUpdate = dB!!.updatePassword(user, password)
                if (checkPasswordUpdate) {
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    Toast.makeText(
                        this@ResetActivity,
                        "Password updated successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(this@ResetActivity, "Password not updated", Toast.LENGTH_SHORT)
                        .show()
                }
            } else {
                Toast.makeText(this@ResetActivity, "Passwords don't match", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}