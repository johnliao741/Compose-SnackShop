package com.jazzhipster.snackshop.remote.repository

import com.jazzhipster.snackshop.remote.model.*
import com.jazzhipster.snackshop.remote.service.SnackShopService
import javax.inject.Inject

class SnackShopRepository @Inject constructor(val snackShopService: SnackShopService) {
    suspend fun getSubHomeTitle(request: BaseRequest):GetSubHomeTitleResponse{
        return snackShopService.getSubHomeTitle(request)
    }

    suspend fun getSubHomeList(request:GetSubHomeListRequest):GetListResponse{
        return snackShopService.getSubHomeList(request)
    }

    suspend fun getMarketList(request: BaseRequest):GetListResponse{
        return snackShopService.getMarketList(request)
    }

    suspend fun getSearchList(request: GetSearchRequest):GetSearchListResponse{
        return snackShopService.getSearchList(request)
    }

    suspend fun getSnackDetail(request:GetSnackDetailRequest):GetSnackDetailResponse{
        return snackShopService.getSnackDetail(request)
    }

}