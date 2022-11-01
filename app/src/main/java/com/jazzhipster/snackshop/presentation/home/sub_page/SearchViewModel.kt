package com.jazzhipster.snackshop.presentation.home.sub_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jazzhipster.snackshop.remote.model.BaseRequest
import com.jazzhipster.snackshop.remote.model.GetSearchListResponse
import com.jazzhipster.snackshop.remote.model.GetSearchRequest
import com.jazzhipster.snackshop.remote.repository.SnackShopRepository
import com.jazzhipster.snackshop.remote.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    val snackShopRepository: SnackShopRepository
) : ViewModel() {
    val _getSearchListFlow = MutableSharedFlow<Result<GetSearchListResponse>>()

    //LiveData
    val getSearchListLiveData = _getSearchListFlow.debounce(150).asLiveData()

    fun getSearchList(query: String = "", isInitLoad: Boolean = false) = viewModelScope.launch {
        flow {
            emit(
                snackShopRepository.getSearchList(
                    GetSearchRequest(query)
                )
            )
        }.onStart {
                if (isInitLoad)
                    _getSearchListFlow.emit(Result.Loading)
            }.catch {
                _getSearchListFlow.emit(Result.Error(it))
            }
            .collectLatest {
                _getSearchListFlow.emit(Result.Success(it))
            }
    }

}