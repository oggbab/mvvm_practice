package basic_test

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.mvvm.R
import kotlinx.android.synthetic.main.activity_test.*
import java.io.*
import java.lang.Exception


class TestActivity : AppCompatActivity() {

    private lateinit var fm : FragmentManager
    private lateinit var ft : FragmentTransaction
    val btnList = arrayListOf<String>("noti", "viewpager", "spinner", "tab", "toolbar", "draw", "dialog", "thread", "file")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        init()
    }



    private fun init() {
        setFragment()
        setList()
    }

    private fun setList() {
        val adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, btnList)
        listview.adapter = adapter
        listview.setOnItemClickListener {
            _, _, position, _ ->
            when (position) {
                0 -> setNoti()
                1 -> setViewPager()
                2 -> setSpinner()
                3 -> setTab()
                4 -> setToolbar()
                5 -> setDraw()
                6 -> setDialog()
                7 -> setThread()
                8 -> setFile()
            }
        }
    }

    private fun setFragment() {
        fm = supportFragmentManager
        ft = fm.beginTransaction()
    }

    private fun addFragment(fragment: Fragment) {

    }

    lateinit var fos : FileOutputStream
    lateinit var fis : FileInputStream

    private fun setFile() {

        val file = File(filesDir, "file3.txt")
        file.createNewFile()
        try {
            //기본
//            fos = FileOutputStream(file)
//            val bufferedOutputStream = BufferedOutputStream(fos)
//            fos.write(35)
//            fos.close()

            //text 파일 최적화
            val fileWriter = FileWriter(file)
            val bufferedWriter = BufferedWriter(fileWriter)
            bufferedWriter.write("파일 입출력")
            bufferedWriter.close()

        } catch (e : Exception) {
            e.printStackTrace()
        }

        if (file.exists() && file.canRead()) {
            try {
                //기본
//                fis = FileInputStream(file)
//                val bufferedInputStream = BufferedInputStream(fis)

                //텍스트 파일
                val fileReader = FileReader(file)
                val bufferedReader = BufferedReader(fileReader)

                Log.e("soon2", "${bufferedReader.read()}")
                Log.e("soon2", "${bufferedReader.readText()}")
                bufferedReader.close()

                val char = bufferedReader.read()

                //한글자 씩 읽기
                while (char != -1) {

                }

            } catch (e : Exception) {

            }
            fis.close()
        }
    }

    private fun setNoti() {
        Toast.makeText(this@TestActivity, "test", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@TestActivity, NotiActivity::class.java)
        val id = "1"
        intent.putExtra("id", id)
        intent.putExtra("extra", "Hello")
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(
            "1",
            "noti",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val builder = NotificationCompat.Builder(this, notificationChannel.id)
            .setContentTitle("타이틀")
            .setTicker("서브타이틀")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setWhen(System.currentTimeMillis())
            .setDefaults(Notification.DEFAULT_ALL)

        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(1, builder.build())
    }

    private fun setThread() {
        Thread().run{
            Thread.sleep(1000)
        }
    }

    private fun setDialog() {
        val list = arrayOf<String>("바나나", "귤", "사과")
        var items = listOf(list)
        AlertDialog.Builder(this)
            .setTitle("Title")
            .setMessage("Message")
//            .setView(EditText(this))
            .setMultiChoiceItems(list, null
            ) { _, index, isChecked -> Toast.makeText(this@TestActivity, list[index] + "를 선택", Toast.LENGTH_SHORT).show() }
            .setPositiveButton("예") { _, _ -> Toast.makeText(this@TestActivity, "예를 선택", Toast.LENGTH_SHORT).show() }
            .setNegativeButton("아니오") { _, _ -> Toast.makeText(this@TestActivity, "아니를 선택", Toast.LENGTH_SHORT).show() }
            .show()
    }

    private fun setDraw() {
        //캔버스 생성
        val bitmap = Bitmap.createBitmap(2000, 2000, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bitmap)
        canvas.drawColor(Color.WHITE)

        //ImageView에 비트맵 연결
        iv.setImageBitmap(bitmap)

        //붓의 색과 두께
        val paint = Paint()
        paint.color = Color.RED
        paint.strokeWidth = 30f

        //좌표
        val rect = RectF(150f,150f,300f,300f)
        //해당 좌표에 사각형 그리기
        canvas.drawRect(rect, paint)

        //해당 좌표에 타원 그리기
        val rect1 = RectF(300f,300f,450f,450f)
        canvas.drawArc(rect1, 0f, 360f, true, paint)

        //해당 좌표에 원 그리기
        val rect2 = RectF(450f,450f,600f,600f)
        canvas.drawArc(rect2, 0f, 360f, true, paint)

        //덜찬 원 그리기
        val rect3 = RectF(600f,600f,750f,750f)
        canvas.drawArc(rect3, 90f, 180f, false, paint)

    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_appbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
                var intent = Intent(this, SecActivity::class.java)
                tv_toolbar.text = "SEARCH"
                startActivityForResult(intent, Activity.RESULT_OK)
            }

            R.id.action_account ->
                tv_toolbar.text = "ACCOUNT"

            R.id.action_settings ->
                tv_toolbar.text = "SETTINGS"
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        tv_toolbar.text = "FInnnnnnnnnnnn"

        if (requestCode == Activity.RESULT_OK) {
            tv_toolbar.text = "FInnnnnnnnnnnn"

        }
    }

    private fun setTab() {
        tabHost.setup()

        val tabspec = tabHost.newTabSpec("Tab Spec 1")
        tabspec.setContent(R.id.content1)
        tabspec.setIndicator("Tab 1")
        tabHost.addTab(tabspec)

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

    private fun setSpinner() {
        val arr = resources.getStringArray(R.array.strArr)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, arr)
        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when(position) {
                    position -> { Toast.makeText(this@TestActivity, arr[position].toString(), Toast.LENGTH_LONG).show() }
                }
            }

        }
    }

    private fun setRecycler() {
        val lm = LinearLayoutManager(this)
        lm.orientation = LinearLayoutManager.VERTICAL
        val adapter = RecyclerAdapter()
//        recycler.adapter = adapter
//        recycler.layoutManager = lm
    }

    private fun setViewPager() {
        val adapter = ViewPagerAdapter()
        viewpager.adapter = adapter
    }
}