package com.jazzhipster.snackshop.remote.repository

import com.jazzhipster.snackshop.remote.model.BaseRequest
import com.jazzhipster.snackshop.remote.model.GetSubHomeListRequest
import com.jazzhipster.snackshop.remote.model.GetListResponse
import com.jazzhipster.snackshop.remote.model.GetSubHomeTitleResponse
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

}