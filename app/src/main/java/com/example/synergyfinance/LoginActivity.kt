package com.example.synergyfinance

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btnLogin.setOnClickListener {
            var intent = Intent(this, LoanActivity::class.java)
            startActivity(intent)
        }

        textViewRegister.setOnClickListener {
            var intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }
    }
}