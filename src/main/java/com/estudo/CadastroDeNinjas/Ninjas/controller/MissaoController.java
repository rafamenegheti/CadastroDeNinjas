package com.estudo.CadastroDeNinjas.Ninjas.controller;

import org.springframework.data.domain.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<MissaoResponse> getMissaoModels(@PageableDefault(size = 10) Pageable pageable) {
        return missaoService.getAllMissoes(pageable);
    }

    @PostMapping
    public Long createMissao(@RequestBody MissaoCreateRequest request) {
        return missaoService.createMissaoWithNinjas(request).getId();
    }
}
