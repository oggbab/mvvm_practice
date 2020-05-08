package basic_test.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvm.R
import kotlinx.android.synthetic.main.fragment_thread.*
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class ThreadFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_thread, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setThread()

        val newRunnable = NewRunnable()
        val thread = Thread(newRunnable)

        thread.start()
    }

    lateinit var handler: Handler
    lateinit var runnable: Runnable

    private fun setThread() {

        handler = Handler()

        runnable = Runnable {
            val cal = Calendar.getInstance()
            val sdf = SimpleDateFormat("HH:mm:ss")
            val strTime = sdf.format(cal.time)
            tv_clock.text = strTime
        }

    }

    inner class NewRunnable : Runnable {
        override fun run() {
            while (true) {
                try {
                    Thread.sleep(1000)
                } catch (e : Exception) {
                    e.printStackTrace()
                }
//                handler.post(runnable)
                activity!!.runOnUiThread(runnable)
            }
        }
    }


}