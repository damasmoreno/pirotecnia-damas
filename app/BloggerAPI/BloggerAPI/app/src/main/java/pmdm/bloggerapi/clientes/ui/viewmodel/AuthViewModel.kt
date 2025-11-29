package pmdm.bloggerapi.clientes.ui.viewmodel




import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import pmdm.bloggerapi.clientes.data.ClienteRepository
import pmdm.bloggerapi.clientes.domain.LoginUseCase
import pmdm.bloggerapi.clientes.ui.model.Cliente
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase) : ViewModel() {

    // Estados de autenticación
    private val _isAuthenticated = MutableStateFlow(false)
    val isAuthenticated: StateFlow<Boolean> = _isAuthenticated.asStateFlow()

    private val _currentUser = MutableStateFlow<Cliente?>(null)
    val currentUser: StateFlow<Cliente?> = _currentUser.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    /**
     * Realiza el login del usuario
     * @param correoelectronico Correo electrónico
     * @param password Contraseña
     */
    fun login(correoelectronico: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                if (correoelectronico.isBlank() || password.isBlank()) {
                    _errorMessage.value = "Por favor, completa todos los campos"
                    _isLoading.value = false
                    return@launch
                }

                if (!isValidEmail(correoelectronico)) {
                    _errorMessage.value = "Por favor, ingresa un email válido"
                    _isLoading.value = false
                    return@launch
                }

                val result = loginUseCase(correoelectronico, password)

                if (result.isSuccess) {
                    val cliente = result.getOrNull()
                    _currentUser.value = result.getOrNull()
                    _isAuthenticated.value = true
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = result.exceptionOrNull()?.message ?: "Error en la autenticación"
                }

            } catch (e: Exception) {
                _errorMessage.value = "Error de conexión: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    /**
     * Cierra la sesión del usuario
     */
    fun logout() {
        viewModelScope.launch {
            try {
                _currentUser.value?.id?.let { clienteId ->

                }
            } catch (e: Exception) {

            } finally {

                _currentUser.value = null
                _isAuthenticated.value = false
                _errorMessage.value = null
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    /**
     * Valida el formato del email
     * @param email Email a validar
     * @return true si el email es válido
     */
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}


