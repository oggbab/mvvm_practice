package basic_test.library

data class WeeklyBoxOfficeList (
    val rnum : String,
    val rank : String,
    val movieNm : String,
    val openDt : String
)

data class boxOfficeResult (
    val boxofficeType : String,
    val showRange : String,
    val yearWeekTime : String,
    val weeklyBoxOfficeList : List<WeeklyBoxOfficeList>
) {
}