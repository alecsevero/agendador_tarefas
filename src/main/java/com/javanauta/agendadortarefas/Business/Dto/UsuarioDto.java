package com.javanauta.agendadortarefas.Business.Dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDto {

    private String email;
    private String senha;
}
