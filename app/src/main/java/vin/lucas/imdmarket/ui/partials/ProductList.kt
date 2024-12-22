package vin.lucas.imdmarket.ui.partials

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
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
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import vin.lucas.imdmarket.R
import vin.lucas.imdmarket.entities.Product
import vin.lucas.imdmarket.ui.activities.products.EditActivity

@Composable
fun ProductList(
    context: ComponentActivity,
    products: List<Product>,
    modifier: Modifier = Modifier,
) {
    LazyColumn (
        modifier = modifier,
    ) {
        items(products) { product -> ProductRow(context, product) }
    }
}

@Composable
fun ProductRow(
    context: ComponentActivity,
    product: Product,
) {
    val codeBoxColor = MaterialTheme.colorScheme.surfaceVariant

    Row (
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box (
            contentAlignment = Alignment.Center,
            modifier = Modifier.width(64.dp)
                .padding(end = 12.dp)
                .drawBehind {
                    drawRoundRect(
                        color = codeBoxColor,
                        cornerRadius = CornerRadius(16f),
                    )
                },
        ) {
            Box (
                modifier = Modifier.padding(8.dp),
            ) {
                Text(
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                    fontFamily = FontFamily.Monospace,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = product.code.toString(),
                )
            }
        }
        Box (
            modifier = Modifier
                .weight(1f)
                .padding(end = 12.dp),
        ) {
            Text(
                text = product.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        Box {
            TextButton(
                modifier = Modifier.size(48.dp),
                shape = CircleShape,
                onClick = {
                    val editIntent = Intent(context, EditActivity::class.java)
                    editIntent.putExtra("product", product.id)

                    context.startActivity(editIntent)
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = stringResource(R.string.edit_content_description)
                )
            }
        }
    }
}
