package com.unit.alaram.braodcoastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.format.DateFormat
import com.unit.alaram.AlaramService
import com.unit.alaram.constant.Constant
import io.karn.notify.Notify
import java.util.*
import java.util.concurrent.TimeUnit


class AlramBrodCast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val timemils = intent.getLongExtra(Constant.EXTRA_EXECT_ALRAM, 0L)
        val st: String? = null
        when (intent.action) {
            Constant.EXTRA_EXECT_ALRAM -> {
                buildNotification(context, "set exect time", converDate(timemils))
            }
            Constant.ACTION_SET_REPETIVE_ALARM -> {
                val cal = Calendar.getInstance().apply {
                    this.timeInMillis = timemils + TimeUnit.DAYS.toMillis(7)
                }
                AlaramService(context).repeatAlaram(cal.timeInMillis)
                buildNotification(context, "set Repet value", converDate(timemils))
            }
        }
    }

    private fun buildNotification(context: Context, title: String, message: String) {
        Notify
            .with(context)
            .content {
                this.title = title
                this.text = "Alram On $message"
            }
            .show()
    }

    private fun converDate(timemilisecon: Long): String =

        DateFormat.format("dd/MM/yyyy HH:mm:ss", timemilisecon).toString()


}