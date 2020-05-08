package basic_test.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.mvvm.R
import kotlinx.android.synthetic.main.layout_music_service.*

class ServiceFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = LayoutInflater.from(container?.context).inflate(R.layout.layout_music_service, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_start.setOnClickListener {
            val intent = Intent(activity, MusicService::class.java)
            activity?.startService(intent)
        }

        btn_stop.setOnClickListener {
            val intent = Intent(activity, MusicService::class.java)
            activity?.stopService(intent)
        }

        btn_bind.setOnClickListener {
            if (mBound) {
                Toast.makeText(activity, "num is ${mService.random}", Toast.LENGTH_LONG).show()
            }
        }

    }

    override fun onStart() {
        super.onStart()

        val intent = Intent(activity, BoundService::class.java)
        activity?.bindService(intent, mConnection, Context.BIND_AUTO_CREATE)
    }

    lateinit var mService: BoundService
    var mBound = false
    var mBound2 = false

    private val mConnection = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            mBound = false
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BoundService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}