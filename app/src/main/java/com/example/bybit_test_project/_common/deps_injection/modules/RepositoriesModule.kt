package com.example.bybit_test_project._common.deps_injection.modules

import com.example.bybit_test_project.data.network.api.ByBitApi
import com.example.bybit_test_project.data.repositories.AnnouncementsRepositoryImpl
import com.example.bybit_test_project.presentation.dependencies.AnnouncementsRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun providesAnnouncementsRepository(byBitApi: ByBitApi): AnnouncementsRepository {
        return AnnouncementsRepositoryImpl(
            byBitApi = byBitApi
        )
    }

}