package com.jazzhipster.snackshop.remote.service

import com.jazzhipster.snackshop.remote.model.*

interface SnackShopService {

    suspend fun getSubHomeTitle(request: BaseRequest):GetSubHomeTitleResponse

    suspend fun getSubHomeList(request: GetSubHomeListRequest):GetListResponse

    suspend fun getMarketList(request: BaseRequest):GetListResponse

    suspend fun getSearchList(request: GetSearchRequest):GetSearchListResponse

    suspend fun getSnackDetail(request: GetSnackDetailRequest):GetSnackDetailResponse
}