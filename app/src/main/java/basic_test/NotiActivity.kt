package basic_test

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import com.mvvm.R
import kotlinx.android.synthetic.main.noti.*

class NotiActivity : Activity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.noti)

        val bundle = intent.extras as Bundle
        val id = bundle.getInt("1")
        val extra = bundle.getString("1")

        noti_tv.text = "id: ${id.toString()} extra: $extra"
    }
}