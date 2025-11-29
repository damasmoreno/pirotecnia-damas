package pmdm.bloggerapi.clientes.ui.screens



import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pmdm.bloggerapi.clientes.ui.model.Cliente
import pmdm.bloggerapi.clientes.ui.model.Pedido
import pmdm.bloggerapi.clientes.ui.viewmodel.AuthViewModel
import pmdm.bloggerapi.clientes.ui.viewmodel.MiCuentaViewModel
import pmdm.bloggerapi.navigation.DetallePedido
import pmdm.bloggerapi.ui.theme.BlackTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MiCuentaScreen(
    navController: NavController,
    authViewModel: AuthViewModel,
    miCuentaViewModel: MiCuentaViewModel = hiltViewModel()
) {
    // Estados de autenticación y datos
    val currentUser by authViewModel.currentUser.collectAsState()
    val isAuthenticated by authViewModel.isAuthenticated.collectAsState()
    val message by miCuentaViewModel.message.collectAsState()
    val pedidos by miCuentaViewModel.pedidos.collectAsState()
    val isLoadingPedidos by miCuentaViewModel.isLoadingPedidos.collectAsState()

    // Redirigir a login si no está autenticado
    LaunchedEffect(isAuthenticated) {
        if (!isAuthenticated) {
            navController.navigate("login") {
                popUpTo("mi_cuenta") { inclusive = true }
            }
        }
    }

    // Cargar pedidos cuando cambie el usuario actual
    LaunchedEffect(currentUser) {
        currentUser?.id?.let { clienteId ->
            miCuentaViewModel.loadPedidos(clienteId)
        }
    }

    LaunchedEffect(message) {
        message?.let {
            println("Mensaje: $it")
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Cuenta") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlackTopBar,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                }

            )
        }
    ) { innerPadding ->
        if (currentUser != null) {
            // Contenido cuando hay usuario
            VerCuentaContent(
                cliente = currentUser!!,
                onLogout = {
                    authViewModel.logout()
                    navController.navigate("login") {
                        popUpTo(0) { inclusive = true }
                    }
                },
                message = message,
                pedidos = pedidos,
                isLoadingPedidos = isLoadingPedidos,
                onPedidoClick = { pedido ->
                    navController.navigate(DetallePedido(pedido.id))
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text(
                    text = "Cargando información...",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }
    }
}

@Composable
fun VerCuentaContent(
    cliente: Cliente,
    onLogout: () -> Unit,
    message: String?,
    pedidos: List<Pedido>,
    isLoadingPedidos: Boolean,
    onPedidoClick: (Pedido) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Sección de información personal
        Text(
            text = "Información Personal",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        message?.let { msg ->
            Text(
                text = msg,
                color = if (msg.contains("Error", ignoreCase = true)) Color.Red else Color.Green,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
        // Tarjeta con información del cliente
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                InfoRow(
                    label = "Nombre completo",
                    value = cliente.Nombre ?: "No disponible"
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                InfoRow(
                    label = "Correo electrónico",
                    value = cliente.correo_electronico
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                InfoRow(
                    label = "NIF",
                    value = cliente.nif
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                InfoRow(
                    label = "Nacionalidad",
                    value = cliente.nacionalidad
                )
                Divider(modifier = Modifier.padding(vertical = 8.dp))

                InfoRow(
                    label = "Fecha de nacimiento",
                    value = cliente.fechaNacimiento
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Mis Pedidos",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (isLoadingPedidos) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()
                    Text(
                        text = "Cargando pedidos...",
                        modifier = Modifier.padding(top = 8.dp),
                        color = Color.Gray
                    )
                }
            }
        } else if (pedidos.isNotEmpty()) {
            // Lista de pedidos
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(pedidos) { pedido ->
                    PedidoItem(pedido = pedido, onPedidoClick = onPedidoClick)
                }
            }
        } else {
            // Estado vacío de pedidos
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray.copy(alpha = 0.2f)
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "📦",
                        style = MaterialTheme.typography.headlineMedium
                    )
                    Text(
                        text = "No tienes pedidos realizados",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
        // Botón de cerrar sesión
        Button(
            onClick = onLogout,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) {
            Text("Cerrar Sesión")
        }
    }
}

@Composable
fun PedidoItem(
    pedido: Pedido,
    onPedidoClick: (Pedido) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .clickable { onPedidoClick(pedido) },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Pedido Nº ${pedido.id}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = when (pedido.entregado) {
                        true -> "✅ Entregado"
                        false -> "🕐 Pendiente"
                        null -> "❓ Sin estado"
                    },
                    style = MaterialTheme.typography.bodySmall,
                    color = when (pedido.entregado) {
                        true -> Color.Green
                        false -> Color.Red
                        null -> Color.Gray
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Fecha: ${pedido.fechaPedido ?: "No disponible"}",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )

            Text(
                text = "Total: ${pedido.total ?: "N/A"}€",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 4.dp)
            )

            if (pedido.fechaPedido == null) {
                Text(
                    text = "⚠️ Fecha no disponible",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Column(
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}