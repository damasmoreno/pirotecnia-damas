package pmdm.bloggerapi.clientes.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import pmdm.bloggerapi.clientes.ui.model.LineaVenta
import pmdm.bloggerapi.clientes.ui.viewmodel.DetallePedidoViewModel
import pmdm.bloggerapi.ui.theme.BlackTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetallePedidoScreen(
    navController: NavController,
    pedidoId: Long,
    viewModel: DetallePedidoViewModel = hiltViewModel()
) {

    // Cargar líneas de venta cuando cambie el pedidoId
    LaunchedEffect(pedidoId) {
        if (pedidoId > 0L) {
            viewModel.loadLineasVenta(pedidoId)
        }
    }

    val lineasVenta by viewModel.lineasVenta.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val message by viewModel.message.collectAsState()

    // Calcular total del pedido
    val totalPedido = lineasVenta.sumOf { it.total }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pedido Nº $pedidoId")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlackTopBar,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Text(
                            text = "Cargando artículos...",
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }
            } else {
                if (pedidoId == 0L) {

                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Error: No se pudo cargar el pedido",
                            color = Color.Red
                        )
                    }
                } else {

                    // Resumen del pedido
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Resumen del Pedido",
                                style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text("Número de pedido: " +
                                    "Nº $pedidoId")
                            Text("Total: ${String.format("%.2f", totalPedido)}€")
                            Text("Artículos: ${lineasVenta.size}")
                        }
                    }


                    // Lista de artículos
                    Text(
                        text = "Artículos del Pedido",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    if (lineasVenta.isNotEmpty()) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            items(lineasVenta) { linea ->
                                LineaVentaItem(lineaVenta = linea)
                            }
                        }
                    } else {
                        // Estado vacío
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(32.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text("📦")
                                Text(
                                    text = "No hay artículos en este pedido",
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                        }
                    }


                    message?.let { msg ->
                        Text(
                            text = msg,
                            color = Color.Red,
                            modifier = Modifier.padding(top = 16.dp)
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun LineaVentaItem(lineaVenta: LineaVenta) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Información del artículo
            Column(
                modifier = Modifier.weight(2f)
            ) {
                Text(
                    text = lineaVenta.descripcionArticulo,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${lineaVenta.unidades} uds × ${String.format("%.2f", lineaVenta.precio)}€",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }


            // Total de la línea
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "${String.format("%.2f", lineaVenta.total)}€",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = BlackTopBar
                )
                Text(
                    text = "Total",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}



