package pmdm.bloggerapi.articulos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pmdm.bloggerapi.articulos.ui.model.CarritoItem
import pmdm.bloggerapi.articulos.ui.viewmodel.CarritoHelperViewModel
import pmdm.bloggerapi.core.managers.CarritoManager
import pmdm.bloggerapi.ui.theme.BlackTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarritoScreen(
    navController: NavController,
    clienteId: Long,
) {
    // Estados del carrito
    val carritoItems = CarritoManager.getItemsState()
    val totalCarrito by CarritoManager.getTotalState()
    val isLoading by CarritoManager.getIsLoadingState()
    val message by CarritoManager.getMessageState()


    // ViewModel para casos de uso
    val viewModel: CarritoHelperViewModel = hiltViewModel()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val refreshKey = remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Carrito") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlackTopBar,
                    titleContentColor = androidx.compose.ui.graphics.Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Volver",
                            tint = androidx.compose.ui.graphics.Color.White
                        )
                    }
                }
            )
        },
        bottomBar = {
            if (carritoItems.isNotEmpty()) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shadowElevation = 8.dp,
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Total: ${String.format("%.2f", totalCarrito)}€",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    CarritoManager.finalizarPedido(
                                        clienteId = clienteId,
                                        crearPedidoUseCase = viewModel.crearPedidoUseCase,
                                        crearLineaVentaUseCase = viewModel.crearLineaVentaUseCase
                                    )
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            enabled = !isLoading,
                            colors = ButtonDefaults.buttonColors(containerColor = BlackTopBar)
                        ) {
                            if (isLoading) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(20.dp),
                                    color = androidx.compose.ui.graphics.Color.White
                                )
                            } else {
                                Text("Finalizar Pedido")
                            }
                        }
                    }
                }
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        if (carritoItems.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Carrito vacío",
                        modifier = Modifier.size(64.dp),
                        tint = androidx.compose.ui.graphics.Color.Gray
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Tu carrito está vacío",
                        style = MaterialTheme.typography.titleMedium,
                        color = androidx.compose.ui.graphics.Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Añade artículos desde el catálogo",
                        style = MaterialTheme.typography.bodyMedium,
                        color = androidx.compose.ui.graphics.Color.Gray
                    )
                }
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(
                    items = carritoItems,
                    key = { it.articulo.ID_Articulo ?: 0L }
                ) { item ->
                    CarritoItemCard(
                        item = item,
                        onQuantityChange = { nuevaCantidad ->
                            CarritoManager.actualizarCantidad(item.articulo, nuevaCantidad)
                            refreshKey.value++
                        },
                        onRemove = {
                            CarritoManager.eliminarDelCarrito(item.articulo)
                            refreshKey.value++
                        }
                    )
                }
            }
        }
    }

    LaunchedEffect(refreshKey.value) { }

    LaunchedEffect(message) {
        message?.let {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(it)
                CarritoManager.clearMessage()
            }
        }
    }
}

@Composable
fun CarritoItemCard(
    item: CarritoItem,
    onQuantityChange: (Int) -> Unit,
    onRemove: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.articulo.descripcion,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${item.articulo.precio}€",
                    style = MaterialTheme.typography.bodyMedium,
                    color = BlackTopBar
                )
                Text(
                    text = "Total: ${String.format("%.2f", item.getTotal())}€",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { onQuantityChange(item.cantidad - 1) },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Reducir")
                }

                Text(
                    text = item.cantidad.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                IconButton(
                    onClick = { onQuantityChange(item.cantidad + 1) },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Aumentar")
                }
            }

            IconButton(
                onClick = onRemove,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar", tint = androidx.compose.ui.graphics.Color.Red)
            }
        }
    }
}

