package com.jazzhipster.snackshop.presentation.common

import android.util.Log
import androidx.compose.runtime.*
import com.jazzhipster.snackshop.remote.result.Result


@Composable
fun <T> ShowView(
    state: State<Result<T>?>,
    successAction: @Composable (T)->Unit) {
    var showLoadingDialog by remember {
        mutableStateOf(false)
    }
    if (showLoadingDialog) {
        LoadingDialog {

        }
    }
    Log.e("status","Loading")
    when (val result = state.value) {
        is Result.Loading -> {
            showLoadingDialog = true
            Log.e("status","Loading")
        }
        is Result.Success -> {
            Log.e("status","Success")
            showLoadingDialog = false
            successAction(result.data)
        }
        is Result.Error -> {
            showLoadingDialog = false
        }

    }
}