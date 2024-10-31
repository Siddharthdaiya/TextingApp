package com.example.textingapp

import android.content.Intent
import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class Login : AppCompatActivity() {
    private lateinit var edtEmail:EditText
    private lateinit var edtPassword:EditText
    private lateinit var btnLogin:Button
    private lateinit var btnSignup:Button

    private lateinit var mAuth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth=FirebaseAuth.getInstance()

        edtEmail=findViewById(R.id.et_email)
        edtPassword=findViewById(R.id.et_Password)
        btnLogin=findViewById(R.id.btn_login)
        btnSignup=findViewById(R.id.btn_signup)

        btnSignup.setOnClickListener {
            val intent=Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener {
            val email=edtEmail.text.toString()
            val password=edtPassword.text.toString()

            login(email,password);
        }

    }

    private fun login(email:String,password:String){
//login for loggin in user
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                   //code for loggin user
                    val intent= Intent(this@Login,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
             Toast.makeText(this,"User does not exist",Toast.LENGTH_SHORT).show()
                }
            }

    }




}