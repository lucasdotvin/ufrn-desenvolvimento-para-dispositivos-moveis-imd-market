package vin.lucas.imdmarket.services

import vin.lucas.imdmarket.contracts.ProductRepository
import vin.lucas.imdmarket.contracts.ProductService
import vin.lucas.imdmarket.contracts.ServiceContainer
import vin.lucas.imdmarket.entities.Product

class DefaultProductService(
    serviceContainer: ServiceContainer,
    productRepository: ProductRepository,
) : ProductService {
    private val products: MutableList<Product> = mutableListOf()

    private val nextId: Int get() = (products.maxOfOrNull { it.id ?: 0 } ?: 0) + 1

    init {
        this.products.addAll(productRepository.load())

        serviceContainer.onTerminate {
            productRepository.dump(products)
        }
    }

    override fun store(product: Product) {
        require(product.id == null) {
            "O ID do produto não pode já estar definido"
        }

        validateIncomingProduct(product)

        product.id = nextId
        products.add(product)
    }

    override fun get(): List<Product> {
        return products.toList()
    }

    override fun removeByCode(code: Int): Boolean {
        val product = products.find { it.code == code } ?: return false

        products.remove(product)

        return true
    }

    override fun findById(productId: Int): Product? {
        return products.find { it.id == productId }
    }

    override fun update(product: Product) {
        require(product.id != null) {
            "O ID do produto deve estar definido"
        }

        require(products.find { it.id == product.id } != null) {
            "Produto não encontrado"
        }

        validateIncomingProduct(product)

        products.replaceAll {
            if (it.id == product.id) {
                product
            } else {
                it
            }
        }
    }

    private fun validateIncomingProduct(product: Product) {
        require(product.code > 0) {
            "O código do produto deve ser maior que zero"
        }

        require(products.find { it.code == product.code && it.id != product.id } == null) {
            "Já existe um produto com o código ${product.code}"
        }

        require(product.name.isNotBlank()) {
            "O nome do produto não pode ser vazio"
        }

        require(product.description.isNotBlank()) {
            "A descrição do produto não pode ser vazia"
        }

        require(product.stock >= 0) {
            "O estoque do produto não pode ser negativo"
        }
    }
}
