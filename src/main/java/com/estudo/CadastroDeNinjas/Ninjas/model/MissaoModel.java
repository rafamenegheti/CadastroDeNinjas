package com.estudo.CadastroDeNinjas.Ninjas.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_missoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MissaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomeDaMissao;
    
    private String dificuldade;
    
    // Uma missão pode ter vários ninjas
    @OneToMany(mappedBy = "missao")
    private List<NinjaModel> ninjas = new ArrayList<>();
} 
