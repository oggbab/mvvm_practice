package basic_test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mvvm.R
import kotlinx.android.synthetic.main.layout_test.*

class TestUI : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_test)
        button.setOnClickListener {
            Toast.makeText(this, "Clicked!", Toast.LENGTH_LONG).show()
        }
    }


}