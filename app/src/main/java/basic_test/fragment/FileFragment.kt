package basic_test.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mvvm.R
import kotlinx.android.synthetic.main.fragment_file.*
import java.io.*
import java.lang.Exception

class FileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_file, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setFile()
    }

    lateinit var fos : FileOutputStream
    lateinit var fis : FileInputStream

    private fun setFile() {

        val file = File(activity!!.filesDir, "file3.txt")
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

                tv_file.text = bufferedReader.readText()
                bufferedReader.close()

                val char = bufferedReader.read()

                //한글자 씩 읽기
                while (char != -1) {

                }

            } catch (e : Exception) {

            }
        }
    }


}