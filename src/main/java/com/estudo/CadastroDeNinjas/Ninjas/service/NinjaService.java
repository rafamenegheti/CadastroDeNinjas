package com.estudo.CadastroDeNinjas.Ninjas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.CadastroDeNinjas.Ninjas.model.NinjaModel;
import com.estudo.CadastroDeNinjas.Ninjas.repository.NinjaRepository;

@Service
public class NinjaService {

    @Autowired
    private NinjaRepository ninjaRepository;

    public List<NinjaModel> getAllNinjas(){
        return ninjaRepository.findAll();
    }

    public NinjaModel getNinjaById(Long id){
        return ninjaRepository.findById(id).orElse(null);
    }

    public NinjaModel createNinja(NinjaModel ninja){    
        return ninjaRepository.save(ninja);
    }
}
