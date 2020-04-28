package basic_test

import java.io.File
import java.io.FileOutputStream

class FileIO {
    val file = File("file.bin")
    val fos = FileOutputStream(file)
    var buf = arrayOfNulls<Byte>(5)

}