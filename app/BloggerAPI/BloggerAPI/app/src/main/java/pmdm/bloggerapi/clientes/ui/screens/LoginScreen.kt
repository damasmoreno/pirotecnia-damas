package pmdm.bloggerapi.clientes.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pmdm.bloggerapi.R
import pmdm.bloggerapi.clientes.ui.viewmodel.AuthViewModel
import pmdm.bloggerapi.ui.theme.BlackTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    // Estados locales para los campos de texto
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()
    val errorMessage by authViewModel.errorMessage.collectAsState()
    val isLoading by authViewModel.isLoading.collectAsState()


    // Redirigir a main si se autentica exitosamente
    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            navController.navigate("main") {
                popUpTo("login") { inclusive = true }
            }
        }
    }


    // Limpiar error cuando cambien los campos
    LaunchedEffect(email, password) {
        if (errorMessage != null) {
            authViewModel.clearError()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Acceso") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlackTopBar,
                    titleContentColor = Color.White
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Logo de la aplicación
            Image(
                painter = painterResource(id = R.drawable.pirotecnia_damas),
                contentDescription = "Logo Pirotecnia Damas",
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = 32.dp)
            )

            // Campo de email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo electrónico") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                enabled = !isLoading
            )

            // Campo de contraseña
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                enabled = !isLoading
            )


            errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Botón de login
            Button(
                onClick = {
                    authViewModel.login(email, password)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                enabled = !isLoading,
                colors = ButtonDefaults.buttonColors(
                    containerColor = BlackTopBar)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = Color.White
                    )
                } else {
                    Text("Acceder")
                }
            }

        }
    }
}

