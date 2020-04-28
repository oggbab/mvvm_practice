package basic_test

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvm.R
import kotlinx.android.synthetic.main.noti.*

class NotiFragment : Fragment() {

    private var mId  = 0
    private lateinit var mExtra : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mId = arguments?.getInt("id")!!
        mExtra = arguments?.getString("extra").toString()
        return inflater.inflate(R.layout.fragment_noti, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        noti_tv.text = "id: $mId extra: $mExtra"
    }
}