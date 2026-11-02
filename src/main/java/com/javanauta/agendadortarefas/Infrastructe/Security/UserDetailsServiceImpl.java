package com.javanauta.agendadortarefas.Infrastructe.Security;

import com.javanauta.agendadortarefas.Business.Dto.UsuarioDto;
import com.javanauta.agendadortarefas.Infrastructe.Client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UsuarioClient client;

    public UserDetails carregaDadosUsuario(String email, String token) {
        UsuarioDto usuarioDto = client.buscaUsuarioPorEmail(email, token);
        return User
                .withUsername(usuarioDto.getEmail()) // Define o nome de usuário como o e-mail
                .password(usuarioDto.getSenha()) // Define a senha do usuário
                .build(); // Constrói o objeto UserDetails
    }
}
