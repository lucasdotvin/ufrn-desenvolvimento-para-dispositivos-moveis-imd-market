package vin.lucas.imdmarket.services

import android.content.Context
import vin.lucas.imdmarket.R
import vin.lucas.imdmarket.contracts.ProductRepository
import vin.lucas.imdmarket.contracts.ProductService
import vin.lucas.imdmarket.contracts.ServiceContainer
import vin.lucas.imdmarket.repositories.SqliteProductRepository

class DefaultServiceContainer(context: Context) : ServiceContainer {
    override val terminationListeners = mutableListOf<() -> Unit>()

    override val productRepository: ProductRepository by lazy {
        SqliteProductRepository(
            context,
            context.getString(R.string.sqlite_database_name),
        )
    }

    override val productService: ProductService by lazy {
        DefaultProductService(this, productRepository)
    }
}
