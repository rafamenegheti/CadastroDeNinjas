package com.estudo.CadastroDeNinjas.Ninjas.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.estudo.CadastroDeNinjas.Ninjas.DTO.MissaoResponse;
import com.estudo.CadastroDeNinjas.Ninjas.DTO.NinjaResponse;
import com.estudo.CadastroDeNinjas.Ninjas.model.MissaoModel;
import com.estudo.CadastroDeNinjas.Ninjas.model.NinjaModel;

public class MissaoMapper {

    public static MissaoResponse toResponse(MissaoModel missao) {
        List<NinjaResponse> ninjas = missao.getNinjas().stream()
            .map(MissaoMapper::toNinjaResponse)
            .collect(Collectors.toList());

        return new MissaoResponse(
            missao.getId(),
            missao.getNomeDaMissao(),
            missao.getDificuldade(),
            ninjas
        );
    }

    private static NinjaResponse toNinjaResponse(NinjaModel ninja) {
        return new NinjaResponse(
            ninja.getId(),
            ninja.getNome(),
            ninja.getEmail(),
            ninja.getIdade()
        );
    }
}