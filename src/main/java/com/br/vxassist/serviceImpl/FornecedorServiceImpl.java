package com.br.vxassist.serviceImpl;

import com.br.vxassist.exception.IdNotFound;
import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.filter.FornecedorFilter;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.model.QFornecedor;
import com.br.vxassist.repository.FornecedorRepository;
import com.br.vxassist.service.FornecedorService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Service
public class FornecedorServiceImpl implements FornecedorService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    @Override
    public Page<Fornecedor> getAll(FornecedorFilter fornecedorFilter, Pageable pageable) {
        QFornecedor qFornecedor = QFornecedor.fornecedor;

        BooleanBuilder where = new BooleanBuilder();
        if(Objects.nonNull(fornecedorFilter.id)){
            where.and(qFornecedor.id.eq(fornecedorFilter.id));
        }

        if(Objects.nonNull(fornecedorFilter.nome) && !fornecedorFilter.nome.trim().isEmpty()){
            where.and(qFornecedor.nome.likeIgnoreCase("%"+fornecedorFilter.nome.toLowerCase()+"%"));
        }

        if(Objects.nonNull(fornecedorFilter.razaoSocial) && !fornecedorFilter.razaoSocial.trim().isEmpty()){
            where.and(qFornecedor.razaoSocial.likeIgnoreCase(fornecedorFilter.razaoSocial));
        }

        if(Objects.nonNull(fornecedorFilter.cnpj) && !fornecedorFilter.cnpj.trim().isEmpty()){
            where.and(qFornecedor.cnpj.likeIgnoreCase("%"+fornecedorFilter.cnpj+"%"));
        }

        if(Objects.nonNull(fornecedorFilter.cidade)){
            where.and(qFornecedor.cidade.eq(fornecedorFilter.cidade));
        }

        return fornecedorRepository.findAll(where, pageable);
    }

    @Override
    public Fornecedor save(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @Override
    public Fornecedor findFornecedorById(Long id) {
        return fornecedorRepository.findById(id).orElseThrow(IdNotFound::new);
    }
}
