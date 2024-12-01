package vin.lucas.imdmarket.products

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import vin.lucas.imdmarket.R
import vin.lucas.imdmarket.ui.theme.IMDMarketTheme

class DeleteActivity : ComponentActivity() {
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
                                Text("Deletar Produtos")
                            },
                        )
                    },
                    content = { paddingValues ->
                        Surface(
                            modifier = Modifier.padding(paddingValues),
                        )
                        {
                            Delete(
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
fun Delete(
    context: ComponentActivity,
    modifier: Modifier = Modifier,
) {
    var code by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = code,
            onValueChange = { code = it },
            label = { Text("Código") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                code = ""
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Clear,
                contentDescription = stringResource(id = R.string.add_content_description),
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(16.dp),
            )
            Text(
                text = "Limpar",
                style = MaterialTheme.typography.bodySmall,
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                Toast.makeText(context, "Produto deletado com sucesso!", Toast.LENGTH_SHORT).show()
                context.finish()
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = stringResource(id = R.string.delete_content_description),
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(20.dp),
            )
            Text(text = "Deletar")
        }
    }
}
