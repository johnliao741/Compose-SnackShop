package com.jazzhipster.snackshop.remote.model

class GetSearchListResponse(
       val recentSearches:List<String>,
       val suggests:List<String>,
       val type:SearchResultType
):BaseResponse() {
}

enum class SearchResultType(val type:Int){
       DEFAULT(0),
       SEARCH(1)
}