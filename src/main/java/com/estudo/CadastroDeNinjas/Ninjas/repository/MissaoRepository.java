package com.estudo.CadastroDeNinjas.Ninjas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudo.CadastroDeNinjas.Ninjas.model.MissaoModel;


public interface MissaoRepository extends JpaRepository<MissaoModel, Long> {
    
}
