package com.example.bybit_test_project._common.deps_injection.modules

import com.example.bybit_test_project.data.network.api.ByBitApi
import com.example.bybit_test_project.data.network.ProviderHttpClient
import dagger.Module
import dagger.Provides
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.URLProtocol
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesHttpClient(): ProviderHttpClient {
        return ProviderHttpClient
    }

    @Provides
    fun providesByBitClient(providerHttpClient: ProviderHttpClient): ByBitApi {
        return ByBitApi(providerHttpClient.getHttpClient().config {
            defaultRequest {
                url{
                    protocol = URLProtocol.HTTPS
                    host = "api.bybit.com"
                }
            }
        })
    }

}