package com.example.textingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtPassword: EditText
   private lateinit var edtName: EditText
    private lateinit var btnSignup: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth=FirebaseAuth.getInstance()
        edtEmail=findViewById(R.id.et_email)
        edtName=findViewById(R.id.et_name)
        edtPassword=findViewById(R.id.et_Password)
        btnSignup=findViewById(R.id.btn_signup)

btnSignup.setOnClickListener {
    val name=edtName.text.toString()
    val email=edtEmail.text.toString()
    val password=edtPassword.text.toString()

    signup(name,email,password);
}

    }

    private fun signup(name:String,email:String,password:String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    // Sign in success, update UI with the signed-in user's information
                    //code for jumping  to home
                    val intent= Intent(this@SignUpActivity,MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"Some Error Occured", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun addUserToDatabase(name:String,email:String,uid:String){
mDbRef=FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))
    }


}