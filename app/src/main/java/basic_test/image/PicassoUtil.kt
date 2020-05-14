package basic_test.image

import android.widget.ImageView
import com.squareup.picasso.Picasso

class PicassoUtil {

    companion object {

        fun setPicasso(view : ImageView) {
            val url = "https://i.imgur.com/DvpvklR.jpg"
            Picasso.get().load(url).into(view)
//            picasso.load(R.drawable.ic_menu).into(iv_picasso)
//            picasso.load("file:///android_asset/picasso_test.png").into(iv_picasso)
        }
    }
}