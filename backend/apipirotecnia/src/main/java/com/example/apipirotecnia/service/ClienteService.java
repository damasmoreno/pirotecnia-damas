package com.example.apipirotecnia.service;

import com.example.apipirotecnia.model.Cliente;
import com.example.apipirotecnia.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepo;

    /**
     * Busca un cliente por email y password (autenticación)
     * NOTA: Este método no es eficiente para grandes volúmenes de datos
     * ya que carga todos los clientes y filtra en memoria.
     * @param email Correo electrónico del cliente
     * @param password Contraseña del cliente
     * @return Cliente si las credenciales son correctas, null en caso contrario
     */
    public Cliente findByEmailAndPassword(String email, String password) {
        for (Cliente cliente : clienteRepo.findAll()) {
            if (cliente.getCorreo_electronico() != null &&
                    cliente.getCorreo_electronico().equals(email) &&
                    cliente.getPassword() != null &&
                    cliente.getPassword().equals(password)) {
                return cliente;
            }
        }
        return null;
    }
}