package pmdm.bloggerapi.articulos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pmdm.bloggerapi.articulos.ui.model.Articulo
import pmdm.bloggerapi.articulos.ui.viewmodel.ArticuloViewModel
import pmdm.bloggerapi.clientes.ui.viewmodel.AuthViewModel
import pmdm.bloggerapi.core.managers.CarritoManager
import pmdm.bloggerapi.ui.theme.BlackTopBar
import pmdm.bloggerapi.utils.ImageUtils

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticulosPorSeccionScreen(
    navController: NavController,
    seccionId: Long,
    seccionNombre: String,
    viewModel: ArticuloViewModel = hiltViewModel(),
    authViewModel: AuthViewModel? = null
) {
    // Estados observables del ViewModel
    val articulos by viewModel.articulos.collectAsState()
    val message by viewModel.message.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Estado del usuario y carrito
    val currentUser by authViewModel?.currentUser?.collectAsState() ?: remember { mutableStateOf(null) }
    val cantidadCarrito = CarritoManager.cantidad

    // Estado para snackbar y coroutine scope
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    // Cargar artículos cuando cambia el seccionId
    LaunchedEffect(seccionId) {
        viewModel.getArticulosPorSeccion(seccionId)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = seccionNombre,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BlackTopBar,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver a categorías",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    // Icono del carrito con badge
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                val clienteId = currentUser?.id ?: 0L
                                if (clienteId != 0L) {
                                    navController.navigate(pmdm.bloggerapi.navigation.Carrito(clienteId = clienteId))
                                } else {
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

                        // Mostrar badge si hay items en el carrito
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
                }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = BlackTopBar)
            }
            return@Scaffold
        }

        if (articulos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "No hay artículos en esta categoría",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                }
            }
            return@Scaffold
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(innerPadding)
                .background(Color(0xFFF8F9FA)),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(articulos) { articulo ->
                ArticuloCard(
                    articulo = articulo,
                    navController = navController,
                    onAddToCart = {
                        CarritoManager.agregarAlCarrito(articulo)
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("${articulo.descripcion} añadido al carrito")
                        }
                    }
                )
            }
        }
    }

    // Manejar mensajes del ViewModel
    LaunchedEffect(message) {
        message?.let {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(it)
                viewModel.clearMessage()
            }
        }
    }
}

@Composable
fun ArticuloCard(articulo: Articulo, navController: NavController, onAddToCart: () -> Unit) {
    val imageResource = ImageUtils.getImageResource(articulo.imagenNombre)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp),
                clip = false
            )
            .clickable {
                onAddToCart()
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.9f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFFF1F3F4))
            ) {
                Image(
                    painter = painterResource(id = imageResource),
                    contentDescription = "Imagen de ${articulo.descripcion}",
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )

                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(6.dp)
                ) {
                    Text(
                        text = "${articulo.precio}€",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color(0xFFFF6B35),
                                        Color(0xFFFF8E53)
                                    )
                                ),
                                shape = RoundedCornerShape(20.dp)
                            )
                            .padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Información del artículo
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = articulo.descripcion,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(0xFF333333),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "${articulo.precio}€",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = BlackTopBar,
                    fontSize = 18.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
