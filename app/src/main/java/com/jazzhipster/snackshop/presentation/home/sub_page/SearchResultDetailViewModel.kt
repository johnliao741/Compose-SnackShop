package com.jazzhipster.snackshop.presentation.home.sub_page

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jazzhipster.snackshop.remote.model.*
import com.jazzhipster.snackshop.remote.repository.SnackShopRepository
import com.jazzhipster.snackshop.remote.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultDetailViewModel @Inject constructor(
    val snackShopRepository: SnackShopRepository
) : ViewModel() {
    val _getSnackDetailFlow = MutableSharedFlow<Result<GetSnackDetailResponse>>()
    val addShoppingCartFlow = MutableSharedFlow<Result<AddShoppingCartResponse>>()
    val saveWishListFlow = MutableSharedFlow<Result<SaveWishListResponse>>()

    //LiveData
    val getSnackDetailLiveData = _getSnackDetailFlow.asLiveData()

    fun getSnackDetail(id: String) = viewModelScope.launch {
        flow {
            emit(
                snackShopRepository.getSnackDetail(
                    GetSnackDetailRequest(id)
                )
            )
        }.onStart {
            _getSnackDetailFlow.emit(Result.Loading)
        }.catch {
            _getSnackDetailFlow.emit(Result.Error(it))
        }.collectLatest {
            _getSnackDetailFlow.emit(Result.Success(it))
        }
    }

    fun addShoppingCart(id: String) = viewModelScope.launch {
        flow {
            emit(
                snackShopRepository.addShoppingCart(
                    AddShoppingCartRequest(id)
                )
            )
        }.onStart {
            addShoppingCartFlow.emit(Result.Loading)
        }.catch {
            addShoppingCartFlow.emit(Result.Error(it))
        }.collectLatest {
            addShoppingCartFlow.emit(Result.Success(it))
        }
    }

    fun saveWishList(id: String) = viewModelScope.launch {
        flow {
            emit(
                snackShopRepository.saveWishList(
                    SaveWishListRequest(id)
                )
            )
        }.onStart {
            saveWishListFlow.emit(Result.Loading)
        }.catch {
            saveWishListFlow.emit(Result.Error(it))
        }.collectLatest {
            saveWishListFlow.emit(Result.Success(it))
        }
    }
}