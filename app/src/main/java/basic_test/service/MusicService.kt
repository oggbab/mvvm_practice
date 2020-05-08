package basic_test.service

import android.app.IntentService
import android.app.Service
import android.content.Intent
import android.media.AsyncPlayer
import android.media.MediaPlayer
import android.os.Binder
import android.os.IBinder
import com.mvvm.R
import com.orhanobut.logger.Logger
import java.util.*

class MusicService : Service() {

    lateinit var mPlayer: MediaPlayer

    //화면과 서비스 객체간에 데이터를 전달할때 사용. 필요 없을때 null
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        mPlayer = MediaPlayer.create(this, R.raw.chacha)
        mPlayer.isLooping = false
        Logger.d("${this.javaClass.name.trim()} : onCreate()")
        stopForeground(false)
        stopSelf()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Logger.d("${this.javaClass.name.trim()} : onStartCommand()")
        mPlayer.start()
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Logger.d("${this.javaClass.name.trim()} : onDestroy()")
        mPlayer.stop()
        super.onDestroy()
    }
}

class HandleService : IntentService(HandleService::class.java.simpleName) {

    override fun onHandleIntent(intent: Intent?) {
        //요청받은 인텐트를 직접 구현하는 방식
        //stopSelf() 등을 호출할 필요가 없다
        //오래걸리는 작업을 추천
    }
}

//bind 방식
class BoundService : Service() {

    private val mBinder = LocalBinder()

    override fun onBind(intent: Intent?): IBinder? {
        return mBinder
    }

    var random : Int = 0
        get() = Random().nextInt(100)

    //잘보면 내부상속이다
    //service > 사용자 정의 service > binder
    //서비스 호출하는 쪽에서 BInder 를 받아야만 한다
    //binder는 서비스와 컴포넌트간의 상호작용을 위한 인터페이스이다.
    inner class LocalBinder : Binder() {
        fun getService() : BoundService {
            return this@BoundService
        }
    }


}