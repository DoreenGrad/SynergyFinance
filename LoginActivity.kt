package com.example.synergyfinance

import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    var username: EditText? = null
    var password: EditText? = null
    var login: Button? = null
    var forgot: TextView? = null
    var dB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnRegister.setOnClickListener {
            onBackPressed()
        }

        fun onBackPressed() {
            super.onBackPressed()
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }

        username = findViewById<View>(R.id.username1) as EditText
        password = findViewById<View>(R.id.password1) as EditText
        login = findViewById<View>(R.id.btnGoLog) as Button
        forgot = findViewById<View>(R.id.btnForgot) as TextView
        dB = DBHelper(this)
        login!!.setOnClickListener {
            val user = username!!.text.toString()
            val pass = password!!.text.toString()
            if (user == "" || pass == "") Toast.makeText(
                this@LoginActivity,
                "Please fill in all the fields",
                Toast.LENGTH_SHORT
            ).show() else {
                val checkUserPass = dB!!.checkUsernamePassword(user, pass)
                if (checkUserPass == true) {
                    Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(applicationContext, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LoginActivity, "Invalid Credentials", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        forgot!!.setOnClickListener {
                    val intent = Intent(applicationContext, PasswordActivity::class.java)
                    startActivity(intent)
                }
    }
}
