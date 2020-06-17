package com.br.vxassist.serviceImpl;

import com.br.vxassist.model.Extrato;
import com.br.vxassist.repository.ExtratoRepository;
import com.br.vxassist.service.ExtratoService;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ExtratoServiceImpl implements ExtratoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ExtratoRepository ExtratoRepository;

    @Override
    public Page<Extrato> getAll(Predicate predicate, Pageable pageable) {
        return ExtratoRepository.findAll(predicate, pageable);
    }

    @Override
    public Extrato save(Extrato extrato) {
        return ExtratoRepository.save(extrato);
    }

}
