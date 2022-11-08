package com.jazzhipster.snackshop.presentation.setting_account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jazzhipster.snackshop.remote.model.SettingUserAccountRequest
import com.jazzhipster.snackshop.remote.repository.SnackShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import javax.inject.Inject

@HiltViewModel
class SettingAccountPage1ViewModel @Inject constructor(
    val snackShopRepository: SnackShopRepository
) :ViewModel(){
    val _settingUserAccount = MutableSharedFlow<SettingUserAccountRequest>()
    //LiveData
    val settingUserAccount = _settingUserAccount.asLiveData()
}