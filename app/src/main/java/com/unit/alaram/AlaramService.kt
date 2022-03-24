package com.unit.alaram

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.unit.alaram.braodcoastreceiver.AlramBrodCast
import com.unit.alaram.constant.Constant
import com.unit.alaram.utils.RandomInteger

class AlaramService(var context: Context) {

    val alarammanger: AlarmManager =
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    fun exectAlaram(timemile: Long) {

        setALaram(timemile, getPandingIntent(getIntent().apply {
            action = Constant.ACTION_SET_EXCAT_ALARM
            putExtra(Constant.EXTRA_EXECT_ALRAM, timemile)
        }))

    }

    fun repeatAlaram(timemile: Long) {
        setALaram(timemile, getPandingIntent(getIntent().apply {
            action = Constant.ACTION_SET_REPETIVE_ALARM
            putExtra(Constant.EXTRA_EXECT_ALRAM, timemile)
        }))
    }

    fun setALaram(timemile: Long, pendingintent: PendingIntent) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarammanger.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP, timemile, pendingintent
            )
        } else {
            alarammanger.setExact(AlarmManager.RTC_WAKEUP, timemile, pendingintent)
        }
    }


    private fun getIntent(): Intent = Intent(context, AlramBrodCast::class.java)

    private fun getPandingIntent(intent: Intent) =
        PendingIntent.getBroadcast(
            context,
            RandomInteger.getReandomvalue(), intent, PendingIntent.FLAG_UPDATE_CURRENT

        )
}
