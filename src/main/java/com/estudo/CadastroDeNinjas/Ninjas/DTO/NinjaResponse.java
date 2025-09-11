package com.estudo.CadastroDeNinjas.Ninjas.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NinjaResponse {
    private Long id;
    private String nome;
    private String email;
    private int idade;
}