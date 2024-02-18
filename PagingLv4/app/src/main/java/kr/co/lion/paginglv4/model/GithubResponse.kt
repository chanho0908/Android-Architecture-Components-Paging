package kr.co.lion.paginglv4.model

data class GithubResponse (
    val total_count : Int,
    val incomplete_results : Boolean,
    val items : List<Items>
)