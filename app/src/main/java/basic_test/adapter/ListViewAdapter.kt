package basic_test.adapter

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.ListView

class ListViewAdapter (val context: Context, val view : ListView, val list : ArrayList<String>, listener : ListViewListener) {

    private val mListener = listener
    fun setListView() {
        val adapter = ArrayAdapter<String>(context, R.layout.simple_list_item_1, list)
        view.adapter = adapter
        view.setOnItemClickListener {
            _, _, position, _ ->
            when (position) {
//                0 -> startActivity(Intent(context, NotiActivity::class.java))
//                position -> replaceFragment(fList[position -1])
            }
            mListener.onClicked(position)
        }
    }
}

interface ListViewListener {
    fun onClicked(position: Int)
}