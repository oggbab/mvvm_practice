package basic_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mvvm.R
import kotlinx.android.synthetic.main.activity_sec.*

class SecActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sec)
        btn_back.setOnClickListener{
            startActivity(Intent(this, TestActivity::class.java))
        }
    }
}