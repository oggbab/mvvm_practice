package com.example.mvvmpr.hilt.room

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 필수!
 * @HiltAndroidApp을 통해 Hilt가 적용될 어플리케이션 클래스를 설정해줘야 합니다.
 * */
@HiltAndroidApp
class MainApplication : Application() {
}