package com.jazzhipster.snackshop.presentation.home.sub_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jazzhipster.snackshop.remote.model.BaseRequest
import com.jazzhipster.snackshop.remote.model.GetListResponse
import com.jazzhipster.snackshop.remote.repository.SnackShopRepository
import com.jazzhipster.snackshop.remote.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MarketViewModel @Inject constructor(
    val snackShopRepository: SnackShopRepository
):ViewModel() {

    val _getDataFlow = MutableSharedFlow<Result<GetListResponse>>()

    //LiveData
    val getDataLiveData = _getDataFlow.asLiveData()

    fun getData() = viewModelScope.launch {
        flow {
            emit(snackShopRepository.getMarketList(BaseRequest("userId")))
        }.onStart {
            _getDataFlow.emit(Result.Loading)
        }.catch {
            _getDataFlow.emit(Result.Error(it))
        }.collectLatest {
            _getDataFlow.emit(Result.Success(it))
        }

    }
}