package com.adl.dataprocessing

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        btn_login.setOnClickListener({

            if(checkBox.isChecked){
                editor.putString("username",et_username.text.toString())
                editor.putString("username",et_username.text.toString())
                editor.putBoolean("isRemember",checkBox.isChecked)
                editor.commit()
            }else{
                editor.clear()
                editor.commit()
            }


        })

        val savedUsername = sharedPreference.getString("username","")
        val savedPassword = sharedPreference.getString("password","")
        checkBox.isChecked = sharedPreference.getBoolean("isRemember",false)

        et_username.setText(savedUsername)
        et_password.setText(savedPassword)
        checkBox.setChecked(checkBox.isChecked)

    }
}