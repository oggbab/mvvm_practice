package basic_test.library

data class FakeModel (
    val page : String,
    val per_page : String,
    val total : String,
    val data : List<data>
)

data class data (
    val id : String,
    val email : String,
    val first_name : String,
    val last_name : String,
    val avatar : String
)

data class RepoTest (
    val id : String,
    val createAt : String
)