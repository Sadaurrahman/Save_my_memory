package com.example.save_my_memories

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.save_my_memories.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Registration : AppCompatActivity() {
    var et_password: TextInputEditText? = null
    var et_email: TextInputEditText? = null
    var et_city: TextInputEditText? = null
    var imageSignUp: ImageView? = null
    var btnSignUp: Button? = null
    var mDatabase: FirebaseDatabase? = null
    var mRef: DatabaseReference? = null
    var toolbar: Toolbar? = null
    var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        //        parent_view = findViewById(android.R.id.content);
        intit()
        mDatabase = FirebaseDatabase.getInstance()
        mRef = mDatabase!!.reference.child("Company App")
        progressDialog = ProgressDialog(this)
        btnSignUp!!.setOnClickListener(View.OnClickListener {
            val password = et_password!!.text.toString().trim { it <= ' ' }
            val email = et_email!!.text.toString().trim { it <= ' ' }
            val city = et_city!!.text.toString().trim { it <= ' ' }

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this@Registration, "Enter Email", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            if (TextUtils.isEmpty(city)) {
                Toast.makeText(applicationContext, "Enter Location", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Enter Password", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            if (password.length < 6) {
                Toast.makeText(
                    applicationContext,
                    "Password too short, enter minimum 6 characters!",
                    Toast.LENGTH_SHORT
                ).show()
                return@OnClickListener
            }
            progressDialog!!.setMessage("SignUp Loading...")
            progressDialog!!.show()
            val ref = FirebaseDatabase.getInstance().reference.child("Users").push()
            ref.child("password").setValue(password)
            ref.child("location").setValue(city)
            ref.child("email").setValue(email).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this@Registration, "Registration successful", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(this@Registration, MainActivity::class.java))
                    progressDialog!!.dismiss()
                } else {
                    Toast.makeText(this@Registration, "Registration failed", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    private fun intit() {
        et_password = findViewById(R.id.et_password)
        et_email = findViewById(R.id.et_email)
        et_city = findViewById(R.id.et_city)
        btnSignUp = findViewById(R.id.btnSignup)
    }
}