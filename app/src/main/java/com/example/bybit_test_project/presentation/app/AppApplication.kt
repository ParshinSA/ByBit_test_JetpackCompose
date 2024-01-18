package com.example.bybit_test_project.presentation.app

import android.app.Application
import com.example.bybit_test_project._common.deps_injection.AppComponent
import com.example.bybit_test_project._common.deps_injection.DaggerAppComponent

class AppApplication : Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder()
            .addContext(context = this)
            .build()
    }

}