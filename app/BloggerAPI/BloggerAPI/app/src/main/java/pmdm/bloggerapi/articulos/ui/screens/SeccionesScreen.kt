package pmdm.bloggerapi.articulos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import pmdm.bloggerapi.R
import pmdm.bloggerapi.articulos.ui.viewmodel.ArticuloViewModel
import pmdm.bloggerapi.clientes.ui.viewmodel.AuthViewModel
import pmdm.bloggerapi.navigation.ArticulosPorSeccion
import pmdm.bloggerapi.ui.theme.BlackTopBar


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SeccionesScreen(
    navController: NavController,
    viewModel: ArticuloViewModel = hiltViewModel(),
    authViewModel: AuthViewModel? = null

) {
    // Estados del ViewModel
    val secciones by viewModel.secciones.collectAsState()
    val message by viewModel.message.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    // Estado del usuario actual
    val currentUser by authViewModel?.currentUser?.collectAsState() ?: remember { mutableStateOf(null) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Categorías",
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
                            contentDescription = "Volver",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable {
                                val clienteId = currentUser?.id ?: 0L
                                if (clienteId != 0L){
                                    navController.navigate(pmdm.bloggerapi.navigation.Carrito(clienteId = clienteId))

                                } else {
                                    coroutineScope.launch {
                                        snackbarHostState.showSnackbar("Debes iniciar sesión")
                                    }
                                }
                            }
                    )
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

        if (secciones.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "No hay categorías disponibles",
                        color = Color.Gray,
                        fontSize = 16.sp
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { viewModel.getSecciones() },
                        colors = ButtonDefaults.buttonColors(containerColor = BlackTopBar)
                    ) {
                        Text("Reintentar")
                    }
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
            items(secciones) { seccion ->
                SeccionCard(seccion = seccion, navController = navController)
            }
        }
    }

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
fun SeccionCard(
    seccion: pmdm.bloggerapi.articulos.ui.model.Seccion,
    navController: NavController
) {
    val imageResource = getSeccionImageResource(seccion.imagenNombre)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                seccion.ID_Seccion?.let { id ->
                    navController.navigate(
                        ArticulosPorSeccion(
                            seccionId = id,
                            seccionNombre = seccion.nombre
                        )
                    )
                }
            },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = imageResource),
                contentDescription = "Imagen de ${seccion.nombre}",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = seccion.nombre,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = BlackTopBar,
                modifier = Modifier.fillMaxWidth()
            )

            seccion.descripcion?.let { descripcion ->
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = descripcion,
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    color = Color.Gray,
                    maxLines = 2,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

/**
 * Función utilitaria para mapear nombres de imagen a recursos
 * @param imageName Nombre de la imagen desde la API
 * @return ID del recurso drawable
 */

fun getSeccionImageResource(imageName: String?): Int {
    return when (imageName?.lowercase()) {
        "petardos" -> R.drawable.petardos
        "voladores" -> R.drawable.voladores
        "bengalas" -> R.drawable.bengalas
        "traca_valenciana" -> R.drawable.traca_valenciana
        "traca_china" -> R.drawable.traca_china
        "fuente_pequena" -> R.drawable.fuentes
        "fuente_mediana" -> R.drawable.fuegos
        "fuente_grande" -> R.drawable.fuegos_artificiales
        "baterias" -> R.drawable.baterias
        else -> R.drawable.pirotecnia_damas
    }
}



