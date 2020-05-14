package basic_test.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.mvvm.R
import kotlinx.android.synthetic.main.layout_viewpager2.*


//ViewPager어댑터가아닌 RecyclerView.Adapter를 사용할 수 있음
//미리보기 및 방향지정 기능
//애니메이션 제어 향상
class ViewPager2Util : AppCompatActivity {

    lateinit var mContext: Context

    constructor(context: Context) {
        mContext = context
    }

    fun setViewPager2() {
        viewpager2.adapter = ViewPager2Adapter()
//        viewpager2.adapter = FragmentStateAdapter(Fragment()) // == FragmentStatePagerAdapter

        //방향 지정
        viewpager2.orientation = ViewPager2.ORIENTATION_VERTICAL

        //미리보기
        viewpager2.clipToPadding = false
        viewpager2.clipChildren = false

        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.pageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.pagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        viewpager2.setPageTransformer { page, position ->
            page.translationX = position * -offsetPx
        }
        //안보였다 새로 불러와지는 문
//        notifyDataSetChanged() 대신 아래 사용
//        notifyItemChanged(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_viewpager2)
    }

}

class ViewPager2Adapter(var items: ArrayList<String> = arrayListOf()) :
    RecyclerView.Adapter<ViewPager2Adapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewPager2Adapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_viewpager2, parent, false)
        )
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = "sss"

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv = itemView.findViewById<TextView>(R.id.tv_vp2)
    }

}