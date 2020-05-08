package basic_test.library

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitImpl {

    // 기본 요청
    @GET("/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?")
    fun getBoxOffice(): Call<Any>

    //경로 반영
    @GET("/path/{path}")
    fun getPath(@Path("path") path: String): Call<Any>

    //파라미터 추가
    @GET("param/")
    fun getParam(@Query("param") param: String): Call<Any>

    //Body 추가
    @GET("body/")
    fun getBody(@Body body: Body): Call<Any>

    @GET("/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.json?")
    fun getApi(@Query("key") key: String, @Query("targetDt") targetDt: String): Call<boxOfficeResult>

    @GET("/users")
    fun getData() : Call<List<Repo>>

    @GET("/")
    fun getDatas() : Call<Repos>

    @GET("/api/users")
    fun getUsers(@Query("page") page : String = "2") : Call<FakeModel>


}