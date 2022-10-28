package com.jazzhipster.snackshop.presentation.home.sub_page

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jazzhipster.snackshop.remote.model.BaseRequest
import com.jazzhipster.snackshop.remote.model.GetSubHomeListRequest
import com.jazzhipster.snackshop.remote.model.GetListResponse
import com.jazzhipster.snackshop.remote.model.GetSubHomeTitleResponse
import com.jazzhipster.snackshop.remote.repository.SnackShopRepository
import com.jazzhipster.snackshop.remote.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class SubHomeViewModel @Inject constructor(
    val snackShopRepository: SnackShopRepository
):ViewModel() {
    val _getDataFlow = MutableSharedFlow<Result<Pair<GetSubHomeTitleResponse,GetListResponse>>>()

    val getDataLiveData = _getDataFlow.asLiveData()

    fun getData() = viewModelScope.launch {
        _getDataFlow.emit(Result.Loading)
        try{
            val title = async { snackShopRepository.getSubHomeTitle(
                BaseRequest("userId")
            ) }
            val homeList = async { snackShopRepository.getSubHomeList(
                GetSubHomeListRequest().apply {
                    userId = "userId"
                }
            ) }
            val data = Pair(title.await(),homeList.await())
            _getDataFlow.emit(Result.Success(data))
        }catch (e:Exception){
            _getDataFlow.emit(Result.Error(e))
        }

    }

}