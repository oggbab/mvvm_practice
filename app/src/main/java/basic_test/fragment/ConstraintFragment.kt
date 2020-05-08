package basic_test.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mvvm.R

class ConstraintFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDialog()
    }

    private fun setDialog() {
        val list = arrayOf<String>("바나나", "귤", "사과")
        var items = listOf(list)
        AlertDialog.Builder(activity)
            .setTitle("Title")
            .setMessage("Message")
//            .setView(EditText(this))
            .setMultiChoiceItems(list, null
            ) { _, index, _ -> Toast.makeText(activity, list[index] + "를 선택", Toast.LENGTH_SHORT).show() }
            .setPositiveButton("예") { _, _ -> Toast.makeText(activity, "예를 선택", Toast.LENGTH_SHORT).show() }
            .setNegativeButton("아니오") { _, _ -> Toast.makeText(activity, "아니를 선택", Toast.LENGTH_SHORT).show() }
            .show()
    }
}