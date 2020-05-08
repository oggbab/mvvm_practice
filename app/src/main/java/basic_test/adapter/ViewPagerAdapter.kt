package basic_test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.mvvm.R
import kotlinx.coroutines.channels.consumesAll

class ViewPagerAdapter : PagerAdapter() {

    val intArr = arrayListOf<Int>(1,3,4,5)

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return intArr.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(container.context).inflate(R.layout.item_viewpager, container, false)
        val textView = view.findViewById<TextView>(R.id.tv)
        textView.text = intArr[position].toString()

        return view
    }
}
