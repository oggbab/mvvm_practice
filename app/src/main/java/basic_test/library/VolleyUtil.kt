package basic_test.library

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.orhanobut.logger.Logger

class VolleyUtil() {

    lateinit var mContext: Context
    lateinit var mUrl : String
    lateinit var mQueue : RequestQueue
    lateinit var mRequest: Any

    constructor(context : Context) : this() {
        this.mContext = context
    }

    init {
        setVolley()
    }

    fun setVolley() {
        mQueue = Volley.newRequestQueue(mContext)
//        mUrl = "https://www.google.com"
//        mUrl = "https://api.github.com/users"
        mUrl = "http://pastebin.com/raw/Em972E5s"

        val stringReq = getStringRequest() as StringRequest
        mRequest = getJsonObjectParams()
//        mRequest = getJsonObjectParse()
//        mRequest = getJsonArray()

        //큐에 요청객체 넣
        mQueue.add(stringReq)
        mQueue.add(mRequest as JsonArrayRequest)
    }

    // 문자열 요청 객
    fun getStringRequest() : Any {

        return StringRequest(
            Request.Method.GET,
            mUrl,
            Response.Listener { Logger.json(it.toString()) },
            Response.ErrorListener { Logger.e(it.message.toString()) }
        ).setShouldCache(false)
    }

    //Json 배열 객
    fun getJsonArray() : Any {
        return JsonArrayRequest(
            Request.Method.GET,
            mUrl,
            null,
            Response.Listener {
//                Logger.d(it)
                Logger.json(it.toString())
            },
            Response.ErrorListener {
                Logger.d(it)
            }
        ).setShouldCache(false)
    }

    //응답객체 분
    fun getJsonObjectParams() : Any {
        return JsonArrayRequest(
            Request.Method.GET,
            mUrl,
            null,
            Response.Listener {
//                Logger.d(it)
                for (i in 0 until it.length()) {
                    val student = it.getJSONObject(i)
                    val firstname = student.getString("firstname")
                    val lastname = student.getString("lastname")
                    Logger.d("$firstname + $lastname")
                }
                Logger.json(it.toString())
            },
            Response.ErrorListener {
                Logger.json(it.toString())
            }
        ).setShouldCache(false)
    }

    // POST 방식 파라미터 추
    fun getPostAddParams() : Any {

        mUrl = "https://reqres.in/api/users"
        return object: JsonObjectRequest(
            Method.POST,
            mUrl,
            null,
            Response.Listener {
                val gson = Gson().fromJson(it.toString(), RepoTest::class.java)
                Logger.json(it.toString())
                Logger.d(gson)
            },
            Response.ErrorListener { Logger.d(it.message.toString())}
        ) {
            override fun getParams(): MutableMap<String, String> {
                val map = mutableMapOf<String, String>()
                map["name"] = "꽃순"
                map["job"] = "dog"
                return map
            }
        }.setShouldCache(false)
    }

    //모델 클래스로 파싱
    fun getJsonObjectParse() : Any {
        mUrl = "https://reqres.in/api/users"
        return object: JsonObjectRequest(
            Request.Method.POST,
            mUrl,
            null,
            Response.Listener {
                //gson 으로 파싱
                val gson = Gson().fromJson(it.toString(), RepoTest::class.java)
                Logger.json(it.toString())
                Logger.d(gson)
            },
            Response.ErrorListener { Logger.d(it.message.toString())}
        ) {
            override fun getParams(): MutableMap<String, String> {
                val map = mutableMapOf<String, String>()
                map["name"] = "꽃순"
                map["job"] = "dog"
                return map
            }
        }.setShouldCache(false)
    }
}