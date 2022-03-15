package com.example.h_drone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import com.ramotion.fluidslider.FluidSlider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)



//        DC Motor 1
        val dcMotorState1 = Firebase.database.getReference("DC Motor").child("Motor 1").child("State")       //  ON / OFF state controls
        val dcMotor1Speed = Firebase.database.getReference("DC Motor").child("Motor 1").child("Speed")       //  Speed control
        val dcMotor1Rotation = Firebase.database.getReference("DC Motor").child("Motor 1").child("Rotation") //  Rotation control


//        DC Motor 2
        val dcMotorState2 = Firebase.database.getReference("DC Motor").child("Motor 2").child("State")       //  ON / OFF state controls
        val dcMotor2Speed = Firebase.database.getReference("DC Motor").child("Motor 2").child("Speed")       //  Speed control
        val dcMotor2Rotation = Firebase.database.getReference("DC Motor").child("Motor 2").child("Rotation") //  Rotation control

//        DC Motor 3
        val dcMotorState3 = Firebase.database.getReference("DC Motor").child("Motor 3").child("State")       //  ON / OFF state controls

//        Direction of rotation
        val servoMotor = Firebase.database.getReference("Servo Motor")

//        Altitude change
        val upwardDirection = Firebase.database.getReference("Upward")
        val downwardDirection = Firebase.database.getReference("Downward")


        sbLeftSpeed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvLeftSpeed.text = progress.toString()
                dcMotor1Speed.setValue(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        sbRightSpeed.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                tvRightSpeed.text = progress.toString()
                dcMotor2Speed.setValue(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })

        tbForLeftMotor.setOnClickListener {
            if (tbForLeftMotor.text.toString() == "ON")
                dcMotorState1.setValue(1)
            else
                dcMotorState1.setValue(0)
        }

        tbForRightMotor.setOnClickListener {
            if (tbForRightMotor.text.toString() == "ON")
                dcMotorState2.setValue(1)
            else
                dcMotorState2.setValue(0)
        }

        switchForDirectionRotation.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
                servoMotor.setValue(1)
            else
                servoMotor.setValue(0)
        }

        switchForLeftRotation.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
                dcMotor1Rotation.setValue(1)
            else
                dcMotor1Rotation.setValue(0)
        }

        switchForRightRotation.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked)
                dcMotor2Rotation.setValue(1)
            else
                dcMotor2Rotation.setValue(0)
        }

        upward.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                upwardDirection.setValue(1)
            else
                upwardDirection.setValue(0)
        }

        down.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked)
                downwardDirection.setValue(1)
            else
                downwardDirection.setValue(0)
        }
    }
}
