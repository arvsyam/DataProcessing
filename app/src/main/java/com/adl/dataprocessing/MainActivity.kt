package com.adl.dataprocessing

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.mcdev.splitbuttonlibrary.OnButtonClickListener
import com.mcdev.splitbuttonlibrary.SplitMenu
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main2.*
import java.lang.reflect.Array.setInt

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val sharedPreference = getSharedPreferences("login_data", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        var isSave: Boolean = true

        val savedInput = sharedPreference.getString("input", "")
        val savedSlider = sharedPreference.getString("slide", "0")
        val savedprogress = sharedPreference.getInt("progress", 0)
        val savedOption = sharedPreference.getInt("opt", 0)
        val savedSwitch = sharedPreference.getBoolean("switch", false)

        switch1.setChecked(savedSwitch)
        radioGroup.check(savedOption)
        seekBar.setProgress(savedprogress)
        et_input.setText(savedInput)
        txtSlider.setText(savedSlider)



        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                txtSlider.setText("${p1}")
                if (isSave) {
                    editor.putInt("progress", p1)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })
        radioGroup.setOnCheckedChangeListener(object : RadioGroup.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: RadioGroup?, p1: Int) {

                val res = when (p1) {
                    R.id.rOpt1 -> "Option 1"
                    R.id.rOpt2 -> "Option 2"
                    R.id.rOpt3 -> "Option 3"
                    else -> "No option"
                }
                if (isSave) {
                    editor.putInt("opt", p1)
                }


            }
        })

        switch1.setOnCheckedChangeListener(object : CompoundButton.OnCheckedChangeListener {
            override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
                if (p1) {
                    txtSwitch.setText("ON")
                } else {
                    txtSwitch.setText("OFF")
                }

                if (isSave) {
                    editor.putBoolean("switch", p1)
                }
            }


        })
        var splitBtn = split_btn
        splitBtn.apply {

            setTextColor(R.color.black)
            setIconColor(android.R.color.white)
            setBgColor(android.R.color.holo_orange_light)
            setMenuItems(R.menu.split_menu)
        }

        splitBtn.setOnButtonClickListener(object : OnButtonClickListener {
            override fun onClick(itemId: Int, itemTitle: String?) {
                Log.d("TAG", "onClick: id :$itemId")
                Log.d("TAG", "onClick: title :$itemTitle")

                if (itemId == R.id.save) {

                    editor.putString("input",et_input.text.toString())
                    editor.putString("slide",txtSlider.text.toString())
                    editor.commit()

                    Toast.makeText(this@MainActivity, "Data saved", Toast.LENGTH_SHORT).show()
                    Log.d("TAG", "onClick: send  ")
                }else if (itemId == R.id.clr) {

                    editor.clear()
                    editor.commit()

                    Toast.makeText(this@MainActivity, "data has been removed", Toast.LENGTH_SHORT).show()
                }else if (itemId == R.id.noAct){
                    Toast.makeText(this@MainActivity, "plese select button option", Toast.LENGTH_SHORT).show()
                }
            }
        })


    }
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


//}

