package basic_test

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.mvvm.R
import kotlinx.android.synthetic.main.activity_file.*
import java.io.*


class FileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file)
        init()
    }

    private val fileName = "items.list"
    private lateinit var listview: ListView
    private lateinit var adapter: ArrayAdapter<*>
    private val items = ArrayList<String>()

    private fun init() {
        adapter = ArrayAdapter<Any?>(this, android.R.layout.simple_list_item_single_choice, items as List<Any?>)
        listview1.adapter = adapter
        loadItemsFromFile() ;
        adapter.notifyDataSetChanged();
        setView()
    }

    private fun setView() {
        buttonAdd.isEnabled = false; // 초기 버튼 상태 비활성 상태로 지정.

        buttonAdd.setOnClickListener{
            val strNew = editTextNew.text.toString()
            strNew?.run {
                items.add(strNew)
                editTextNew.setText("")
                adapter.notifyDataSetChanged();
                saveItemsToFile()
            }
        }
        buttonDel.setOnClickListener {
                var count = 0
                var checkedIndex = 0

                count = adapter.count;

                if (count > 0) {
                    checkedIndex = listview.checkedItemPosition;
                    if (checkedIndex > -1 && checkedIndex < count) {
                        items.removeAt(checkedIndex);
                        listview.clearChoices();
                        adapter.notifyDataSetChanged();
                        saveItemsToFile() ;
                    }
                }
            }

        editTextNew.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(edit: Editable?) {
                buttonAdd.isEnabled = edit.toString().isNotEmpty()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun saveItemsToFile() {
        val file = File(filesDir, fileName)
        var fw: FileWriter? = null
        var bufwr: BufferedWriter? = null
        try {
            fw = FileWriter(file)
            bufwr = BufferedWriter(fw)
            for (str in items) {
                bufwr.write(str)
                bufwr.newLine()
            }

            bufwr.flush()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        try {
            bufwr?.close()
            fw?.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun loadItemsFromFile() {
        val file = File(filesDir, fileName)
        var str = ""

        if (file.exists()) {
            try {
                // open file.
                var fr = FileReader(file)
                val bufrd = BufferedReader(fr)
                while (bufrd.readLine().also{ str = it } != null) {
                    items.add(str!!)
                }
                bufrd.close()
                fr.close()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }


}