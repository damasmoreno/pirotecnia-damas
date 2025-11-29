package pmdm.bloggerapi.ui.screens

import androidx.compose.runtime.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import pmdm.bloggerapi.R
import pmdm.bloggerapi.clientes.ui.viewmodel.AuthViewModel
import pmdm.bloggerapi.navigation.MiCuenta
import pmdm.bloggerapi.navigation.VerArticulos
import pmdm.bloggerapi.ui.theme.BlackTopBar
import androidx.compose.animation.core.*
import kotlinx.coroutines.launch
import pmdm.bloggerapi.core.managers.CarritoManager

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    authViewModel: AuthViewModel
) {
    // Estados de autenticación y usuario
    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()
    val currentUser by authViewModel.currentUser.collectAsState()

    // Estado del carrito
    val cantidadCarrito = CarritoManager.cantidad


    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Redirigir a login si no está autenticado
    LaunchedEffect(isAuthenticated) {
        if (!isAuthenticated) {
            navController.navigate("login") {
                popUpTo("main") { inclusive = true }
            }
        }
    }

    val infiniteTransition = rememberInfiniteTransition()
    val alpha = infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Pirotecnia Damas") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlackTopBar,
                    titleContentColor = Color.White
                ),
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                currentUser?.id?.let { clienteId ->
                                    navController.navigate(pmdm.bloggerapi.navigation.Carrito(clienteId = clienteId))
                                } ?: run {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Debes iniciar sesión para ver el carrito")
                                    }
                                }
                            }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Carrito",
                            tint = Color.White
                        )

                        if (cantidadCarrito > 0) {
                            Badge(
                                modifier = Modifier.offset(x = (-8).dp, y = 8.dp)
                            ) {
                                Text(
                                    text = cantidadCarrito.toString(),
                                    color = Color.White,
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }

                    IconButton(
                        onClick = {
                            authViewModel.logout()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Close,
                            contentDescription = "Cerrar Sesión",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        // Contenido principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Opción Catálogo
            Image(
                painter = painterResource(id = R.drawable.catalogo),
                contentDescription = "Catálogo",
                modifier = Modifier
                    .size(150.dp)
                    .clickable {
                        navController.navigate(VerArticulos) // Navegar a secciones
                    }
                    .graphicsLayer(alpha.value)
            )

            Text(
                text = "Catálogo",
                modifier = Modifier.padding(top = 16.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Opción Mi Cuenta
            Image(
                painter = painterResource(id = R.drawable.perfil_del_usuario),
                contentDescription = "Mi cuenta",
                modifier = Modifier
                    .size(150.dp)
                    .clickable {
                        navController.navigate(MiCuenta) // Navegar a mi cuenta
                    }
                    .graphicsLayer(alpha = alpha.value)
            )

            Text(
                text = "Mi Cuenta",
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}