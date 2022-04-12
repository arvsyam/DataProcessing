package com.adl.dataprocessing

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.lang.reflect.Array.setInt

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val sharedPreference = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()

        var isSave:Boolean = true


        seekBar.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                txtSlider.setText("${p1}")
                if(isSave){
                    editor.putInt("progress",p1)
                }



            }

            override fun onStartTrackingTouch(p0: SeekBar?) {


            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        radioGroup.setOnCheckedChangeListener(object: RadioGroup.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {

                val res = when(p1){
                    R.id.rOpt1 ->"Option 1"
                    R.id.rOpt2 ->"Option 2"
                    R.id.rOpt3 ->"Option 3"
                    else->"No option"
                }
                if(isSave){
                    editor.putInt("opt",p1)

                }

                Toast.makeText(this@MainActivity,"Pilihannya adlaah ${res}",Toast.LENGTH_LONG).show()
            }
        })

        switch1.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                if(p1){
                    txtSwitch.setText("ON")
                }else{
                    txtSwitch.setText("Off")
                }
                if(isSave){
                    editor.putBoolean("switch",p1)

                }

            }

        })

        btn_save.setOnClickListener({
            isSave = true
            editor.putString("input",et_input.text.toString())
            editor.putString("slide",txtSlider.text.toString())
            editor.commit()

        })
        btn_clear.setOnClickListener({
            editor.clear()
            editor.commit()
        })
        val savedInput = sharedPreference.getString("input","")
        val savedSlider = sharedPreference.getString("slide","")
        val savedprogress = sharedPreference.getInt("progress",0)
        val savedOption = sharedPreference.getInt("opt",0)
        val savedSwitch = sharedPreference.getBoolean("switch",false)

        switch1.setChecked(savedSwitch)
        radioGroup.check(savedOption)
        seekBar.setProgress(savedprogress)
        et_input.setText(savedInput)
        txtSlider.setText(savedSlider)




        }

//        val sharedPreference = getSharedPreferences("login_data", Context.MODE_PRIVATE)
//        var editor = sharedPreference.edit()
//        btn_login.setOnClickListener({
//
//            if(checkBox.isChecked){
//                editor.putString("username",et_username.text.toString())
//                editor.putString("password",et_password.text.toString())
//                editor.putBoolean("isRemember",checkBox.isChecked)
//                editor.commit()
//            }else{
//                editor.clear()
//                editor.commit()
//            }
//
//
//        })
//
//        val savedUsername = sharedPreference.getString("username","")
//        val savedPassword = sharedPreference.getString("password","")
//        checkBox.isChecked = sharedPreference.getBoolean("isRemember",false)
//
//        et_username.setText(savedUsername)
//        et_password.setText(savedPassword)
//        checkBox.setChecked(checkBox.isChecked)

    }


