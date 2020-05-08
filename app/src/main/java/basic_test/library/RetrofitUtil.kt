package basic_test.library

import android.util.Log
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitUtil {

    //Okhttp3 로그 향상
    fun getIntercepter() : HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message ->
            Log.e("", message + "")
        })
        return interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

    }

    fun setWithoutClient(): RetrofitImpl? {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitImpl::class.java)

        service?.getData()?.enqueue(object : Callback<List<Repo>> {
            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                Logger.d(t.message.toString())
            }

            override fun onResponse(
                call: Call<List<Repo>>,
                response: retrofit2.Response<List<Repo>>
            ) {
                if (response.isSuccessful) {
                    Logger.d(response.body()?.get(0).toString())
                    Logger.d(response.raw())
                    Logger.d(response.code())
                    val userList = response.body()
                    for (i in response.body()!!.indices) {

                    }
//                    Logger.json(response.toString())
                }
            }

        })

        return service
    }

    fun setWithClient() {

        //Okhttp3 클라이언트 생성
        val client = OkHttpClient.Builder().addInterceptor(getIntercepter()).build()

        //레트로핏 객체 생
        val conn = Retrofit.Builder()
            .baseUrl("http://api.github.com")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //사전 정의 한 인터페이스 기준으로 api 제
        val interfaces = conn.create(RetrofitImpl::class.java)

        //인터페이스 메소드를 큐에 삽입 및 콜백 처리
        interfaces.getDatas().enqueue(object: Callback<Repos> {
            override fun onFailure(call: Call<Repos>, t: Throwable) {
            }

            override fun onResponse(call: Call<Repos>, response: retrofit2.Response<Repos>) {
            }
        })
    }

}