package vin.lucas.imdmarket

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import vin.lucas.imdmarket.products.CreateActivity
import vin.lucas.imdmarket.products.DeleteActivity
import vin.lucas.imdmarket.products.EditActivity
import vin.lucas.imdmarket.products.IndexActivity
import vin.lucas.imdmarket.ui.theme.IMDMarketTheme

class MainActivity : ComponentActivity() {
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
                            title = {
                                Text("IMD Market")
                            },
                        )
                    },
                    content = { paddingValues ->
                        Surface(
                            modifier = Modifier.padding(paddingValues),
                        )
                        {
                            Home(
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
fun Home(
    context: ComponentActivity,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            "O que deseja fazer?",
            style = MaterialTheme.typography.titleSmall,
        )
        Spacer(modifier = Modifier.height(12.dp))
        ProductActionButton(
            action = CreateActivity::class.java,
            context = context,
            icon = Icons.Default.Add,
            label = "Cadastrar Produto",
            modifier = Modifier.width(180.dp),
        )
        ProductActionButton(
            action = IndexActivity::class.java,
            context = context,
            icon = Icons.Default.List,
            label = "Listar Produtos",
            modifier = Modifier.width(180.dp),
        )
        ProductActionButton(
            action = EditActivity::class.java,
            context = context,
            icon = Icons.Default.Edit,
            label = "Alterar Produto",
            modifier = Modifier.width(180.dp),
        )
        ProductActionButton(
            action = DeleteActivity::class.java,
            context = context,
            icon = Icons.Default.Delete,
            label = "Deletar Produtos",
            modifier = Modifier.width(180.dp),
        )
    }
}

@Composable
private fun ProductActionButton(
    context: ComponentActivity,
    icon: ImageVector,
    label: String,
    modifier: Modifier,
    action: Class<*>,
) {
    FilledTonalButton(
        onClick = {
            context.startActivity(Intent(context, action))
        },
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = R.string.login_content_description),
            modifier = Modifier
                .padding(end = 4.dp)
                .size(16.dp),
        )
        Column (horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                label,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
