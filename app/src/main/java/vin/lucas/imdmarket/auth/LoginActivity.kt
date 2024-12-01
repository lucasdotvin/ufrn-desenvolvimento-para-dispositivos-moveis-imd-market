package vin.lucas.imdmarket.auth

import android.content.Intent
import android.os.Bundle
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
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import vin.lucas.imdmarket.MainActivity
import vin.lucas.imdmarket.R
import vin.lucas.imdmarket.ui.theme.IMDMarketTheme

class LoginActivity : ComponentActivity() {
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
                                Text("Entrar")
                            },
                        )
                    },
                    content = { paddingValues ->
                        Surface(
                            modifier = Modifier.padding(paddingValues),
                        )
                        {
                            LoginForm(
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
fun LoginForm(
    context: ComponentActivity,
    modifier: Modifier = Modifier
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = login,
            onValueChange = { login = it },
            label = { Text("Login") },
        )
        Spacer(modifier = Modifier.padding(4.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = password,
            onValueChange = { password = it },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            label = { Text("Senha") },
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                context.startActivity(Intent(context, MainActivity::class.java))
            }
        ) {
            Icon(
                imageVector = Icons.Filled.ExitToApp,
                contentDescription = stringResource(id = R.string.login_content_description),
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(20.dp),
            )
            Text(text = "Entrar")
        }
        Spacer(modifier = Modifier.padding(8.dp))
        TextButton(
            onClick = {
                context.startActivity(Intent(context, ForgotActivity::class.java))
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Warning,
                contentDescription = stringResource(id = R.string.forgot_content_description),
                modifier = Modifier
                    .padding(end = 4.dp)
                    .size(16.dp),
            )
            Text(
                text = "Esqueceu sua senha ou login?",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
            )
        }
    }
}
