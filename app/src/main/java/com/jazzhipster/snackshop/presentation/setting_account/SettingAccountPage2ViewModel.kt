package com.jazzhipster.snackshop.presentation.setting_account

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.jazzhipster.snackshop.remote.repository.SnackShopRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class SettingAccountPage2ViewModel @Inject constructor(
    val snackShopRepository: SnackShopRepository
):ViewModel(){

    val userGoals = MutableStateFlow<List<String>>(
        listOf<String>(
            "No goal! Just snacking",
            "Snacking for party time!",
            "Snacking in a healthy way"
        )
    ).asLiveData()
}