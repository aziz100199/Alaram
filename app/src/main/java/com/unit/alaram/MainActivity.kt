package com.unit.alaram

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.unit.alaram.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {
    var bindig: ActivityMainBinding? = null
    private lateinit var alaramservice: AlaramService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindig = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindig?.root)
        alaramservice = AlaramService(this)

        bindig?.alaram?.setOnClickListener {
            setALaram { timeinmile -> alaramservice.exectAlaram(timeinmile) }
        }

        bindig?.repeatalaram?.setOnClickListener {
//            setALaram { timeinmile -> alaramservice.repeatAlaram(timeinmile) }
            setALaram { alaramservice.repeatAlaram(it) }
        }


    }


    private fun setALaram(callback: (Long) -> Unit) {
        Calendar.getInstance().apply {
            this.set(Calendar.SECOND, 0)
            this.set(Calendar.MILLISECOND, 0)
            DatePickerDialog(

                this@MainActivity,
                0,
                { datePicker, year, month, day ->

                    this.set(Calendar.YEAR, year)
                    this.set(Calendar.MONTH, month)
                    this.set(Calendar.DAY_OF_MONTH, day)

                    TimePickerDialog(this@MainActivity,
                        0,

                        { _, houre, minute ->
                            this.set(Calendar.HOUR_OF_DAY, houre)
                            this.set(Calendar.MINUTE, minute)
                            callback(this.timeInMillis)
                            Toast.makeText(this@MainActivity, "Alaram Set", Toast.LENGTH_SHORT)
                                .show()
                        },
                        this.get(Calendar.HOUR_OF_DAY),
                        this.get(Calendar.MINUTE),
                        false
                    ).show()

                },
                this.get(Calendar.YEAR),
                this.get(Calendar.MONTH),
                this.get(Calendar.DAY_OF_MONTH)

            ).show()

        }
    }
}