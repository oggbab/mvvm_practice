package basic_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import basic_test.RecyclerAdapter.*
import com.mvvm.R

class RecyclerAdapter : RecyclerView.Adapter<Viewholder>() {

    val resArr = arrayListOf<Int>(1,3,4,5,6)

    inner class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val textView = itemView.findViewById<TextView>(R.id.tv)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
        val viewholder = Viewholder(view)
        return viewholder
    }

    override fun getItemCount(): Int {
        return resArr.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        holder.textView.text = resArr[position].toString()
    }
}