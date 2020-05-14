package basic_test.util

import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import android.provider.ContactsContract
import com.orhanobut.logger.Logger

class ContactUtil {

    lateinit var contentResolver : String
    companion object {
/*         fun isChangeUsim() {

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

        fun getContacts() {
            val c =  contentResolver.query(
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
            }
        }*/
    }
}