package com.jazzhipster.snackshop.remote.service

import com.jazzhipster.snackshop.remote.model.BaseRequest
import com.jazzhipster.snackshop.remote.model.GetSubHomeListRequest
import com.jazzhipster.snackshop.remote.model.GetListResponse
import com.jazzhipster.snackshop.remote.model.GetSubHomeTitleResponse

interface SnackShopService {

    suspend fun getSubHomeTitle(request: BaseRequest):GetSubHomeTitleResponse

    suspend fun getSubHomeList(request: GetSubHomeListRequest):GetListResponse

    suspend fun getMarketList(request: BaseRequest):GetListResponse
}