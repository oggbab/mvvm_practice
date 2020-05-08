package basic_test.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvm.R
import kotlinx.android.synthetic.main.fragment_tab.*

class TabFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTab()
    }


    private fun setTab() {
        tabHost.setup()

        val tabspec = tabHost.newTabSpec("Tab Spec 1")
        tabspec.setContent(R.id.content1)
        tabspec.setIndicator("Tab 1")
        tabHost.addTab(tabspec)

        val tabspec2 = tabHost.newTabSpec("Tab Spec 2")
        tabspec2.setContent(R.id.content2)
        tabspec2.setIndicator("Tab 2")
        tabHost.addTab(tabspec2)

        val tabspec3 = tabHost.newTabSpec("Tab Spec 3")
        tabspec3.setContent(R.id.content3)
        tabspec3.setIndicator("Tab 3")
        tabHost.addTab(tabspec3)

    }
}