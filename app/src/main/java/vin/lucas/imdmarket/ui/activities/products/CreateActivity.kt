package vin.lucas.imdmarket.ui.activities.products

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
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
import vin.lucas.imdmarket.ui.partials.ProductForm
import vin.lucas.imdmarket.ui.theme.IMDMarketTheme

class CreateActivity : ComponentActivity() {
    private val productService by lazy {
        (this.application as IMDMarketApplication).serviceContainer.productService
    }

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                                Text("Cadastrar Produto")
                            },
                        )
                    },
                    content = { paddingValues ->
                        Surface(
                            modifier = Modifier.padding(paddingValues),
                        )
                        {
                            Create(
                                productService,
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
fun Create(
    productService: ProductService,
    context: ComponentActivity,
    modifier: Modifier = Modifier,
) {
    ProductForm(
        modifier = modifier,
        product = null,
        submitButtonIcon = Icons.Filled.Add,
        submitButtonIconDescription = stringResource(id = R.string.add_content_description),
        submitButtonLabel = stringResource(id = R.string.add_content_description),
        onSubmit = { product ->
            try {
                productService.store(product);
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
                "Produto cadastrado com sucesso!",
                Toast.LENGTH_SHORT,
            ).show()

            context.finish()
        }
    )
}
