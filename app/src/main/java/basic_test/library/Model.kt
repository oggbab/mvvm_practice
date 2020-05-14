package basic_test.library

import java.net.URL

data class Model (
    var stat : String,
    var page : Int,
    var totak_page : Int,
    var photos : ArrayList<Photo>
)

data class Photo(
    var date_token : String,
    var title : String,
    var width :Int,
    var url : URL,
    var height : Int
)

data class Repo(
    val login : String,
    val id : String,
    val node_id : String,
    val avatar_url : String
)

data class Repos(
    val current_user_url : String
)
