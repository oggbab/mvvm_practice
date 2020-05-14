package basic_test.util

import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.PersistableBundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mvvm.R
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.image_parsel.*
import java.util.zip.Inflater

class ParcelTwoActivity : AppCompatActivity() {

    var isIntent = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.image_parsel)

        intent?.let {
            val name = intent.getParcelableArrayListExtra<ParselTest>("list").get(0).name
            tv_parsel.setText(name)
        }
    }
}


class ParcelActivity : AppCompatActivity() {

    var isIntent = false

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.image_parsel)
        setParselIntent()
    }

    private fun setParselIntent() {
        var intent = Intent(this, ParcelTwoActivity::class.java)
        val parselList = arrayListOf<ParselTest>(ParselTest("soon2"), ParselTest("woong"))
        intent.putParcelableArrayListExtra("list", parselList)
        startActivity(intent)
    }
}


@Parcelize
data class ParselTest(val name : String?) : Parcelable {

}

data class ParcelTest(val name: String?) : Parcelable {

    constructor(parcel: Parcel) : this(parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ParcelTest> {
        override fun createFromParcel(parcel: Parcel): ParcelTest {
            return ParcelTest(parcel)
        }

        override fun newArray(size: Int): Array<ParcelTest?> {
            return arrayOfNulls(size)
        }
    }

}