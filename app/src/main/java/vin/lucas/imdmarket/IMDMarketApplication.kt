package vin.lucas.imdmarket

import android.app.Application
import vin.lucas.imdmarket.contracts.ServiceContainer
import vin.lucas.imdmarket.services.DefaultServiceContainer

class IMDMarketApplication : Application() {
    lateinit var serviceContainer: ServiceContainer

    override fun onCreate() {
        super.onCreate()

        serviceContainer = DefaultServiceContainer(this)
    }
}
