package kr.co.lion.paginglv3.newModel

data class GithubResponse (
    val total_count : Int,
    val incomplete_results : Boolean,
    val items : List<NewItems>
)