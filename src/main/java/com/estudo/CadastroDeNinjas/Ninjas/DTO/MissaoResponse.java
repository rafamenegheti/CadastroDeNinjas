package com.estudo.CadastroDeNinjas.Ninjas.DTO;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MissaoResponse {
    private Long id;
    private String nomeDaMissao;
    private String dificuldade;
    private List<NinjaResponse> ninjas;
}