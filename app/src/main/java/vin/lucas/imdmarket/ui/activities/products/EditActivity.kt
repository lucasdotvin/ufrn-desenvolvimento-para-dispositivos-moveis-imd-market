package vin.lucas.imdmarket.ui.activities.products

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import vin.lucas.imdmarket.IMDMarketApplication
import vin.lucas.imdmarket.R
import vin.lucas.imdmarket.contracts.ProductService
import vin.lucas.imdmarket.entities.Product
import vin.lucas.imdmarket.ui.partials.ProductForm
import vin.lucas.imdmarket.ui.theme.IMDMarketTheme

class EditActivity : ComponentActivity() {
    private val productService by lazy {
        (this.application as IMDMarketApplication).serviceContainer.productService
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val productId = this.intent.getIntExtra("product", 0);
        val product = productService.findById(productId);

        if (product == null) {
            Toast.makeText(
                this,
                "Produto não encontrado",
                Toast.LENGTH_SHORT,
            ).show()

            finish()
            return
        }

        setContent {
            IMDMarketTheme {
                Scaffold(
                    topBar = {
                        CenterAlignedTopAppBar(
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                            ),
                            navigationIcon = {
                                IconButton(onClick = { finish() }) {
                                    Icon(
                                        imageVector = Icons.Filled.ArrowBack,
                                        contentDescription = stringResource(R.string.back_content_description)
                                    )
                                }
                            },
                            title = {
                                Text("Alterar Produto")
                            },
                        )
                    },
                    content = { paddingValues ->
                        Surface(
                            modifier = Modifier.padding(paddingValues),
                        )
                        {
                            Edit(
                                product = product,
                                productService = productService,
                                this,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(24.dp),
                            )
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Edit(
    product: Product,
    productService: ProductService,
    context: ComponentActivity,
    modifier: Modifier = Modifier,
) {
    ProductForm(
        modifier = modifier,
        product = product,
        submitButtonIcon = Icons.Filled.Edit,
        submitButtonIconDescription = stringResource(id = R.string.add_content_description),
        submitButtonLabel = stringResource(id = R.string.add_content_description),
        onSubmit = { updatedProduct ->
            try {
                productService.update(updatedProduct);
            } catch (e: IllegalArgumentException) {
                Toast.makeText(
                    context,
                    e.message,
                    Toast.LENGTH_SHORT,
                ).show()

                return@ProductForm
            }

            Toast.makeText(
                context,
                "Produto atualizado com sucesso!",
                Toast.LENGTH_SHORT,
            ).show()

            context.finish()
        }
    )
}
