package com.example.synergyfinance

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    var username: EditText? = null
    var password: EditText? = null
    var repassword: EditText? = null
    var register: Button? = null
    var login: Button? = null
    var dB: DBHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        }

        username = findViewById<View>(R.id.username) as EditText
        password = findViewById<View>(R.id.password) as EditText
        repassword = findViewById<View>(R.id.repassword) as EditText
        register = findViewById<View>(R.id.btnGoReg) as Button
        login = findViewById<View>(R.id.btnLogin) as Button
        dB = DBHelper(this)
        register!!.setOnClickListener {
            val user = username!!.text.toString()
            val pass = password!!.text.toString()
            val repass = repassword!!.text.toString()
            if (user == "" || pass == "" || repass == "") Toast.makeText(
                this@RegisterActivity,
                "Please enter all the fields",
                Toast.LENGTH_SHORT
            ).show() else {
                if (pass == repass) {
                    val checkUser = dB!!.checkUsername(user)
                    if (checkUser == false) {
                        val insert = dB!!.insertData(user, pass)
                        if (insert == true) {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Registered successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(applicationContext, HomeActivity::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@RegisterActivity,
                                "Registration failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "User already exists! Please login",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(this@RegisterActivity, "Passwords do not match", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
        login!!.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

/*import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        }
    }
}
        btnRegister.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right)
    }
}*/