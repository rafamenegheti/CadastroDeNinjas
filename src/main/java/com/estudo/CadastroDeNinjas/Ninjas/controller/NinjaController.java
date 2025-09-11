package com.estudo.CadastroDeNinjas.Ninjas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estudo.CadastroDeNinjas.Ninjas.model.NinjaModel;
import com.estudo.CadastroDeNinjas.Ninjas.service.NinjaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ninja")
public class NinjaController {

    @Autowired
    private NinjaService ninjaService;

    @GetMapping
    public List<NinjaModel> getAllNinjas(){
        return ninjaService.getAllNinjas();
    }

    @PostMapping
    public Long create(@RequestBody NinjaModel ninja) {
        return ninjaService.createNinja(ninja).getId();
    }
    

}
