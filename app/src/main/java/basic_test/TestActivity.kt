package basic_test

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.content.IntentFilter
import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.provider.ContactsContract
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import basic_test.activity.NotiActivity
import basic_test.fragment.*
import basic_test.library.RetrofitUtil
import basic_test.receiver.ReceiverTest
import basic_test.service.MusicService
import basic_test.service.ServiceFragment
import com.bumptech.glide.Glide
import com.mvvm.R
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.contact_layout.*
import kotlinx.android.synthetic.main.image_layout.*


class TestActivity : AppCompatActivity() {

    val menuList = arrayListOf<String>("1","2","3")
    val btnList = arrayListOf<String>("noti", "thread", "viewpager", "spinner", "tab", "recycler", "draw", "dialog", "file")
    val fList = arrayListOf<Fragment>(ThreadFragment(),ViewPagerFragment(),SpinnerFragment(),TabFragment(),RecyclerFragment(),DrawFragment(), DialogFragment(),FileFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_test)
        setContentView(R.layout.activity_test)
        Logger.addLogAdapter(AndroidLogAdapter())
        init()
    }

    private fun init() {
//        setList()
//        setToolbar()
//        setDrawer()
//        setRetrofit()
//        setPicasso()
//        setGlide()
//        isChangeUsim()
//        getContacts()
//        startService()
        startBroadcastReceiver()
    }

    private fun startBroadcastReceiver() {
        //정
        val intent = Intent("go")
        sendBroadcast(intent)
        //동적

    }

    override fun onPause() {
        super.onPause()
        unRegisterReceiver()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver()
    }

    private var mReceiver = ReceiverTest()
    private val BROADCAST_MESSAGE = "go2"
    private var num = 0

    private fun registerReceiver() {
        if (this.mReceiver != null) {
            return
        }

        val filter = IntentFilter()
        filter.addAction(BROADCAST_MESSAGE)

        this.registerReceiver(this.mReceiver, filter)
    }

    private fun unRegisterReceiver() {
        if(mReceiver != null) {
            this.unregisterReceiver(mReceiver);
        }
    }



    private fun startService() {
        replaceFragment(ServiceFragment())
    }

    private fun getContacts() {

/*        val c =  contentResolver.query(
            ContactsContract.CommonDataKinds
                .Phone.CONTENT_URI,  // 조회할 컬럼명
            null, // 조회할 컬럼명
            null, // 조건 절
            null, // 조건절의 파라미터
            null);// 정렬 방향

        var str = ""; // 출력할 내용을 저장할 변수
        c?.moveToFirst(); // 커서를 처음위치로 이동시킴
        do {
            val name = c?.getString(c?.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            val phoneNumbermoveToFirst) {
                = c?    addFra.getString(c?.get
                    ColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            str += "이름 : $name 폰번호 : $phoneNumber $\n"
        } while (c?.moveToNext()!!);//데이터가 없을 때까지반복
        textView2.setText(str);
    */
    }

    private fun isChangeUsim() {


        val observer: ContentObserver =
            object : ContentObserver(Handler()) {
                override fun onChange(
                    selfChange: Boolean,
                    uri: Uri
                ) {
                    super.onChange(selfChange, uri)
                    Logger.e("Usim Chainge : onChange")
                    }
                }
    val AUTHORITY = "${this.packageName}.usim"
    val CONTENTS_URI = Uri.parse("content://$AUTHORITY/")
        contentResolver.registerContentObserver(CONTENTS_URI,true, observer)
    }

    private fun setGlide() {
        val url = "https://i.imgur.com/DvpvklR.jpg"
//            Glide.with(this).load(url).fitCenter().into(iv_picassos)
        val glide = Glide.with(this).load(url)
        glide.placeholder(R.drawable.ic_menu)
        glide.error(R.drawable.ic_menu)
//            glide.fitCenter()
        glide.into(iv_picassos)
    }

    private fun setPicasso() {

        val url = "https://i.imgur.com/DvpvklR.jpg"
        Picasso.get().load(url).into(iv_picassos)
//            picasso.load(R.drawable.ic_menu).into(iv_picasso)
//            picasso.load("file:///android_asset/picasso_test.png").into(iv_picasso)

    }

    private fun setRetrofit() {
        val service = RetrofitUtil().setWithoutClient()

//            service?.getData()?.enqueue(object : Callback<List<Repo>> {
//                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
//                    Logger.d(t.message.toString())
//                }
//
//                override fun onResponse(
//                    call: Call<List<Repo>>,
//                    response: retrofit2.Response<List<Repo>>
//                ) {
//                    if (response.isSuccessful) {
//                        Logger.json(response.body().toString())
//                        Logger.json(response.toString())
//                    }
//                }
//
//            })
    }


    private fun setList() {
    /*    val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, btnList)
        listview.adapter = adapter
        listview.setOnItemClickListener {
            _, _, position, _ ->
            when (position) {
                0 -> startActivity(Intent(this, NotiActivity::class.java))
                position -> replaceFragment(fList[position -1])
            }
        }*/
    }


    private fun setDrawer() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, menuList)
        drawer_menu.adapter = adapter
        drawer_menu.setOnItemClickListener {
                _, _, position, _ -> drawer.closeDrawer(Gravity.LEFT)
        }
    }


        private fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.container, fragment)
        ft.commit()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        val supportActionBar = supportActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를 꺼낼 홈 버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu) // 홈버튼 이미지 변경
        supportActionBar?.setDisplayShowTitleEnabled(false) // 툴바에 타이틀 안보이게
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_appbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> drawer.openDrawer(Gravity.LEFT)
            R.id.action_search -> {
                var intent = Intent(this, NotiActivity::class.java)
                startActivityForResult(intent, Activity.RESULT_OK)
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Activity.RESULT_OK) {
            showToast("ActivityResult Success")
        }
    }

    private fun showToast(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }


}