package com.jazzhipster.snackshop.hilt

import com.jazzhipster.snackshop.remote.mock.SnackShopServiceImp
import com.jazzhipster.snackshop.remote.service.SnackShopService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class NetworkModule {
    @Provides
    fun provideSnackShopService():SnackShopService
    =SnackShopServiceImp()

}