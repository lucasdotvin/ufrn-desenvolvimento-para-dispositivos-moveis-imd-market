package vin.lucas.imdmarket.ui.partials

import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import vin.lucas.imdmarket.R
import vin.lucas.imdmarket.entities.Product

@Composable
fun ProductForm(
    product: Product?,
    modifier: Modifier = Modifier,
    onSubmit: (Product) -> Unit,
    submitButtonIcon: ImageVector,
    submitButtonIconDescription: String,
    submitButtonLabel: String,
) {
    var code by remember { mutableIntStateOf(product?.code ?: 0) }
    var name by remember { mutableStateOf(product?.name ?: "") }
    var description by remember { mutableStateOf(product?.description ?: "") }
    var stock by remember { mutableIntStateOf(product?.stock ?: 0) }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = code.toString(),
            onValueChange = {
                code = it.toIntOrNull() ?: 0
            },
            label = { Text("Código") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.padding(4.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") },
            singleLine = true,
        )
        Spacer(modifier = Modifier.padding(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            value = description,
            onValueChange = { description = it },
            label = { Text("Descrição") },
            singleLine = false,
        )
        Spacer(modifier = Modifier.padding(4.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = stock.toString(),
            onValueChange = {
                stock = it.toIntOrNull() ?: 0
            },
            label = { Text("Estoque") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        )
        Spacer(modifier = Modifier.padding(8.dp))
        TextButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                code = 0
                name = ""
                description = ""
                stock = 0
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
                onSubmit(
                    Product(
                        id = product?.id,
                        code = code.toInt(),
                        name = name,
                        description = description,
                        stock = stock.toInt(),
                    )
                )
            }
        ) {
            Icon(
                imageVector = submitButtonIcon,
                contentDescription = submitButtonIconDescription,
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(16.dp),
            )
            Text(
                text = submitButtonLabel,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
