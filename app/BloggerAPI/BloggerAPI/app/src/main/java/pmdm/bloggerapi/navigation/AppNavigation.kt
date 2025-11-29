package pmdm.bloggerapi.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pmdm.bloggerapi.clientes.ui.screens.LoginScreen
import pmdm.bloggerapi.clientes.ui.viewmodel.AuthViewModel
import pmdm.bloggerapi.ui.screens.MainScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.toRoute
import pmdm.bloggerapi.articulos.ui.screens.ArticulosPorSeccionScreen
import pmdm.bloggerapi.articulos.ui.screens.CarritoScreen
import pmdm.bloggerapi.articulos.ui.screens.SeccionesScreen
import pmdm.bloggerapi.clientes.ui.screens.DetallePedidoScreen
import pmdm.bloggerapi.clientes.ui.screens.MiCuentaScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()


    val authViewModel: AuthViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "login" // Pantalla inicial
    ) {
        // Pantalla de Login
        composable("login") {
            LoginScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        // Pantalla principal
        composable("main") {
            MainScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        // Pantalla de secciones (categorías)
        composable<VerArticulos> {
            SeccionesScreen(navController,
                authViewModel = authViewModel
            )
        }

        // Pantalla de artículos por sección (con parámetros)
        composable<ArticulosPorSeccion> { backStackEntry ->
            val args = backStackEntry.toRoute<ArticulosPorSeccion>()
            ArticulosPorSeccionScreen(
                navController = navController,
                seccionId = args.seccionId,
                seccionNombre = args.seccionNombre,
                authViewModel = authViewModel
            )
        }

        // Pantalla de mi cuenta
        composable<MiCuenta> {
            MiCuentaScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }


        // Pantalla del carrito (con parámetros)
        composable<Carrito> { backStackEntry ->
            val args = backStackEntry.toRoute<Carrito>()
            CarritoScreen(
                navController = navController,
                clienteId = args.clienteId
            )
        }

        // Pantalla de detalle de pedido (con parámetros)
        composable<DetallePedido> { backStackEntry ->
            val args = backStackEntry.toRoute<DetallePedido>()
            DetallePedidoScreen(
                navController = navController,
                pedidoId = args.pedidoId
            )
        }
    }
    }
