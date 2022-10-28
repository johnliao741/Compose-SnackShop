package com.jazzhipster.snackshop.hilt

import com.jazzhipster.snackshop.remote.repository.SnackShopRepository
import com.jazzhipster.snackshop.remote.service.SnackShopService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    fun provideSnackShopRepository(snackShopService: SnackShopService):SnackShopRepository
    = SnackShopRepository(snackShopService)
}