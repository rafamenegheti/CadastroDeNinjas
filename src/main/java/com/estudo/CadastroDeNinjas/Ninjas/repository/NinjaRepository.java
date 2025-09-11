package com.estudo.CadastroDeNinjas.Ninjas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudo.CadastroDeNinjas.Ninjas.model.NinjaModel;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
    
}
