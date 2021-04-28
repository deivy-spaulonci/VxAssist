package com.br.vxassist.postgres.service;

import com.br.vxassist.postgres.model.Despesa;
import com.br.vxassist.postgres.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DespesaServicePost{

    @PersistenceContext
    private EntityManager entityManager;


    private final DespesaRepository despesaRepository;

    @Autowired
    public DespesaServicePost(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    public void save(Despesa despesa){
        despesaRepository.save(despesa);
    }
}
