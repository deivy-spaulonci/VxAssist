package com.br.vxassist.serviceImpl;

import com.br.vxassist.model.Conta;
import com.br.vxassist.model.Despesa;
import com.br.vxassist.repository.ContaRepository;
import com.br.vxassist.service.ContaService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ContaServiceImpl implements ContaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public Page<Conta> getAll(Predicate predicate, Pageable pageable) {
        return contaRepository.findAll(predicate, pageable);
    }

    @Override
    public Conta save(Conta conta){
        return contaRepository.save(conta);
    }


}
