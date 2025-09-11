package com.estudo.CadastroDeNinjas.Ninjas.DTO;

import java.util.List;

import lombok.Data;

@Data
public class MissaoCreateRequest {

    private String nomeDaMissao;
    private String dificuldade;
    private List<Long> ninjaIds;
}
