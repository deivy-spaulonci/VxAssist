package com.br.vxassist.serviceImpl;

import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.model.QFornecedor;
import com.br.vxassist.repository.FornecedorRepository;
import com.br.vxassist.service.FornecedorService;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public Page<Fornecedor> getAll(Predicate predicate, Pageable pageable) {
        return fornecedorRepository.findAll(predicate, pageable);
    }

    @Override
    public Fornecedor save(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

}
