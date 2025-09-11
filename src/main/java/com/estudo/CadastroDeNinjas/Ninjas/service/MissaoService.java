package com.estudo.CadastroDeNinjas.Ninjas.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estudo.CadastroDeNinjas.Ninjas.DTO.MissaoCreateRequest;
import com.estudo.CadastroDeNinjas.Ninjas.DTO.MissaoResponse;
import com.estudo.CadastroDeNinjas.Ninjas.mapper.MissaoMapper;
import com.estudo.CadastroDeNinjas.Ninjas.model.MissaoModel;
import com.estudo.CadastroDeNinjas.Ninjas.model.NinjaModel;
import com.estudo.CadastroDeNinjas.Ninjas.repository.MissaoRepository;

@Service
public class MissaoService {

    @Autowired
    private MissaoRepository missaoRepository;
    @Autowired
    private NinjaService ninjaService;

    public List<MissaoResponse> getAllMissoes(){
        List<MissaoModel> missoes = missaoRepository.findAll();
        return missoes.stream()
              .map(MissaoMapper::toResponse)
              .collect(Collectors.toList());
    }

    public MissaoModel createMissao(MissaoModel missao){
        return missaoRepository.save(missao);
    }

    public MissaoResponse createMissaoWithNinjas(MissaoCreateRequest request){
        MissaoModel missao = new MissaoModel();
        missao.setNomeDaMissao(request.getNomeDaMissao());
        missao.setDificuldade(request.getDificuldade());
    
        if(request.getNinjaIds() != null) {
        for (Long ninjaId : request.getNinjaIds()) {
            NinjaModel ninja = ninjaService.getNinjaById(ninjaId);
    
                ninja.setMissao(missao);           
                missao.getNinjas().add(ninja);     
            }
        }
    
        MissaoModel savedMissao = missaoRepository.save(missao);
        return MissaoMapper.toResponse(savedMissao);
    }
}
