package vin.lucas.imdmarket.products

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import vin.lucas.imdmarket.R
import vin.lucas.imdmarket.ui.theme.IMDMarketTheme

class IndexActivity : ComponentActivity() {
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
                                Text("Listar Produtos")
                            },
                        )
                    },
                    content = { paddingValues ->
                        Surface(
                            modifier = Modifier.padding(paddingValues),
                        )
                        {
                            Index(
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
fun Index(
    context: ComponentActivity,
    modifier: Modifier = Modifier,
) {
    val dashedStroke = Stroke(width = 2f,
        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
    )

    val shapeColor = MaterialTheme.colorScheme.inverseSurface

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .drawBehind {
                    drawRoundRect(
                        color = shapeColor,
                        style = dashedStroke,
                        cornerRadius = CornerRadius(16f)
                    )
                },
        ) {
            Text(
                "Futura lista de produtos...",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.inverseSurface,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            onClick = { context.finish() }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back_content_description),
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(16.dp),
            )
            Text(
                text = "Voltar",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
