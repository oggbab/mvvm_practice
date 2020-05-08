package basic_test.library

public data class WeeklyBoxOfficeList (
    val rnum : String,
    val rank : String,
    val movieNm : String,
    val openDt : String
)

public data class boxOfficeResult (
    val boxofficeType : String,
    val showRange : String,
    val yearWeekTime : String,
    val weeklyBoxOfficeList : List<WeeklyBoxOfficeList>
) {
}