package vin.lucas.imdmarket.contracts

import vin.lucas.imdmarket.entities.Product

interface ProductRepository {
    fun load(): List<Product>

    fun dump(products: List<Product>)
}
