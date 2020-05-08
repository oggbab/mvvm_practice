package com.mvvm

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.LargeTest
import androidx.test.filters.MediumTest
import androidx.test.filters.SdkSuppress
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import basic_test.TestUI
import kotlinx.android.synthetic.main.layout_test.*
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.runner.RunWith
import com.mvvm.R
import org.junit.After


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    //테스트에 사용할 ActivityTestRule과 ServiceTestRule 을 지정스
    //여기서는 Test라는 클래스로 테스트하겠다는 선언
    //코틀린에서는 @JvmField 어노테이션도 추가해야함
    @Rule @JvmField
    public var mActivityRule = ActivityTestRule(TestUI::class.java)

    //살제 테스트할 코드
    @org.junit.Test
    fun listGoesOverTheFold() {

        Espresso.onView(withId(R.id.editText))
            .perform(typeText("puuuuug"))
        //editText 에 Hello World! 입력하고 키보드를 내립니다.
//        Espresso.onView(withId(R.id.editText))
//            .perform(typeText("Hello World!"), closeSoftKeyboard())

        //textView 의 값이 "Hello World!" 인지 확인합니다.
//        Espresso.onView(withId(R.id.textView)).check(matches(withText("Hello World!")))

        //button 을 클릭합니다.
        Espresso.onView(withId(R.id.button)).perform(click())
    }

    //테스트할 sdk버전 지정
//    @SdkSuppress(minSdkVersion = 25, maxSdkVersion = 29)
    //테스트 규모에 따라 지정
//    @SmallTest @LargeTest @MediumTest

    //테스트 끝난후 호출
/*    @After
    fun end() {
        Log.e("soon2", "finish")
    }*/
}

/// 테스트 대상 클래스
class Test : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_test)

        editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                textView.text = p0
            }
        })


        button.setOnClickListener { Toast.makeText(this, textView.text, Toast.LENGTH_SHORT).show() }
    }
}