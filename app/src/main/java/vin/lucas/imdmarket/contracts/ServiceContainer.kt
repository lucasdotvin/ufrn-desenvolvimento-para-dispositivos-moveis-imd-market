package vin.lucas.imdmarket.contracts

interface ServiceContainer {
    val terminationListeners: MutableList<() -> Unit>

    val productRepository: ProductRepository
    val productService: ProductService

    fun onTerminate(listener: () -> Unit) {
        terminationListeners.add(listener)
    }

    fun terminate() {
        terminationListeners.forEach { it() }
    }
}
