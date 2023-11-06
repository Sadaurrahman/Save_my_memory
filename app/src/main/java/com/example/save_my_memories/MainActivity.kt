package com.example.save_my_memories

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.save_my_memories.R
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private var parent_view: View? = null
    var etUserName: TextInputEditText? = null
    var etPassword: TextInputEditText? = null
    var btnLogin: Button? = null
    var btnSetUser: Button? = null
    var tvRegister: TextView? = null
    var mDatabase: FirebaseDatabase? = null
    var mRef: DatabaseReference? = null

    //    SignUpModel signUpModel;
    var textView2: TextView? = null
    var userType = 0
    var progressDialog: ProgressDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        parent_view = findViewById(R.id.content)
        init()
        setListener()
        //        setSupportActionBar(toolbar);
        progressDialog = ProgressDialog(this)
        //        signUpModel=new SignUpModel(this);
        mDatabase = FirebaseDatabase.getInstance()
        mRef = FirebaseDatabase.getInstance().getReference("Company App").child("Users")

//        Tools.setSystemBarColor(this, android.R.color.white);
//        Tools.setSystemBarLight(this);
//        (findViewById(R.id.sign_up_for_account) as View).setOnClickListener {
//            Snackbar.make(
//                it,
//                "Sign up for an account",
//                Snackbar.LENGTH_SHORT
//            ).show()
//        }
    }

    private fun setListener() {

        tvRegister!!.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    Registration::class.java
                )
            )
        }
        btnLogin!!.setOnClickListener(View.OnClickListener {
            val username = etUserName!!.text.toString().trim { it <= ' ' }
            val password = etPassword!!.text.toString().trim { it <= ' ' }
            if (TextUtils.isEmpty(username)) {
                Toast.makeText(applicationContext, "Enter email address!", Toast.LENGTH_SHORT)
                    .show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Enter password!", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
            progressDialog?.setMessage("Login Wait.....")
            progressDialog?.show()

                userLogin(username, password)
        })
    }

    private fun adminLogin(username: String, password: String) {
        val query: Query =
            FirebaseDatabase.getInstance().getReference("Admin").orderByChild("username")
                .equalTo(username)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childSnapshot in dataSnapshot.children) {
                    val signUpModel: String = childSnapshot.child("password").getValue(
                        String::class.java
                    )!!
                    if (signUpModel == password) {
                        // Here is your desired location
                        // AppData.userId = childSnapshot.getKey()
                        //                                AppData.phoneNo = childSnapshot.child("phoneNumber").getValue(String.class);
                        progressDialog!!.dismiss()
                        // AppData.userType = "admin"
                        Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                        //   startActivity(Intent(this@MainActivity, AdminPanel::class.java))
                    } else {
                        progressDialog!!.dismiss()
                        Toast.makeText(this@MainActivity, "failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                progressDialog!!.dismiss()
            }
        })
    }

    private fun init() {
        etUserName = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        tvRegister = findViewById(R.id.tvRegister)
    }

    fun userLogin(username: String?, password: String) {
        val query: Query =
            FirebaseDatabase.getInstance().getReference("Users").orderByChild("email")
                .equalTo(username)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (childSnapshot in dataSnapshot.children) {
                    val signUpModel: String = childSnapshot.child("password").getValue(
                        String::class.java
                    )!!
                    if (signUpModel == password) {
                        // Here is your desired location
//                        AppData.userId = childSnapshot.getKey()
//                        AppData.userType = "user"
                        //                                AppData.phoneNo = childSnapshot.child("phoneNumber").getValue(String.class);
                        progressDialog!!.dismiss()
                        Toast.makeText(this@MainActivity, "Success", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@MainActivity, MainDashboard::class.java))
                        finish()
                    } else {
                        progressDialog!!.dismiss()
                        Toast.makeText(this@MainActivity, "failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
    }

    companion object {
        private const val GALLERY_CODE = 1
    }
}