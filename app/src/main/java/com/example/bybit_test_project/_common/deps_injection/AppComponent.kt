package com.example.bybit_test_project._common.deps_injection

import android.content.Context
import com.example.bybit_test_project._common.deps_injection.modules.NetworkModule
import com.example.bybit_test_project._common.deps_injection.modules.RepositoriesModule
import com.example.bybit_test_project._common.deps_injection.modules.ViewModelsModule
import com.example.bybit_test_project.presentation.app.AppActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoriesModule::class,
        ViewModelsModule::class,
        NetworkModule::class,
    ]
)
interface AppComponent {

    fun inject(appActivity: AppActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun addContext(context: Context): Builder
        fun build(): AppComponent
    }

}