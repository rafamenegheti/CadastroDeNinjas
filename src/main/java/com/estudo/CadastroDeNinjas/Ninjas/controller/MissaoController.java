package com.estudo.CadastroDeNinjas.Ninjas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.CadastroDeNinjas.Ninjas.DTO.MissaoCreateRequest;
import com.estudo.CadastroDeNinjas.Ninjas.DTO.MissaoResponse;
import com.estudo.CadastroDeNinjas.Ninjas.service.MissaoService;

@RestController
@RequestMapping("/missao")
public class MissaoController {

    @Autowired
    private MissaoService missaoService;

    @GetMapping
    public List<MissaoResponse> getMissaoModels() {
        return missaoService.getAllMissoes();
    }

    @PostMapping
    public Long createMissao(@RequestBody MissaoCreateRequest request) {
        return missaoService.createMissaoWithNinjas(request).getId();
    }
}
