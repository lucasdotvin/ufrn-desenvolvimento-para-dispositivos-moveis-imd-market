package vin.lucas.imdmarket.contracts

import vin.lucas.imdmarket.entities.Product

interface ProductService {
    fun store(product: Product)

    fun get(): List<Product>

    fun removeByCode(code: Int): Boolean

    fun findById(productId: Int): Product?

    fun update(product: Product)
}
