package com.br.vxassist.serviceImpl;

import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.model.Despesa;
import com.br.vxassist.model.QDespesa;
import com.br.vxassist.repository.DespesaRepository;
import com.br.vxassist.service.DespesaService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DespesaServiceImpl implements DespesaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DespesaRepository despesaRepository;

    @Override
    public Page<Despesa> getAll(DespesaFilter despesaFilter, Pageable pageable){

        QDespesa qDespesa = QDespesa.despesa;

        BooleanBuilder where = new BooleanBuilder();
        if(Objects.nonNull(despesaFilter.id)){
            where.and(qDespesa.id.eq(despesaFilter.id));
        }
        if(Objects.nonNull(despesaFilter.tipoDespesa)){
            where.and(qDespesa.tipoDespesa.eq(despesaFilter.tipoDespesa));
        }
        if(Objects.nonNull(despesaFilter.fornecedor)){
            where.and(qDespesa.fornecedor.eq(despesaFilter.fornecedor));
        }
        if(Objects.nonNull(despesaFilter.getDataInicial()) && Objects.nonNull(despesaFilter.getDataFinal())){
            where.and(qDespesa.data.between(despesaFilter.getDataInicial(), despesaFilter.getDataFinal()));
        }else if(Objects.nonNull(despesaFilter.getDataInicial()) && Objects.isNull(despesaFilter.getDataFinal())){
            where.and(qDespesa.data.eq(despesaFilter.getDataInicial()).and(qDespesa.data.after(despesaFilter.getDataInicial())));
        }else if(Objects.isNull(despesaFilter.getDataInicial()) && Objects.nonNull(despesaFilter.getDataFinal())){
            where.and(qDespesa.data.eq(despesaFilter.getDataFinal()).and(qDespesa.data.before(despesaFilter.getDataFinal())));
        }
        if(Objects.nonNull(despesaFilter.getFormaPagamento())){
            where.and(qDespesa.formaPagamento.id.eq(despesaFilter.getFormaPagamento().getId()));
        }
        return despesaRepository.findAll(where, pageable);
    }

    @Override
    public Despesa save(Despesa despesa){
        return despesaRepository.save(despesa);
    }

    @Override
    public Long count() {
        return despesaRepository.count();
    }

    @Override
    public BigDecimal total(DespesaFilter despesaFilter) {
        JPAQuery<BigDecimal> query = new JPAQuery<>(this.entityManager);
        QDespesa qDespesa = QDespesa.despesa;

        query.select(qDespesa.valor.sum()).from(qDespesa);

        if(Objects.nonNull(despesaFilter.getId())){
            query.where(qDespesa.id.like(despesaFilter.getId().toString()));
        }
        if(Objects.nonNull(despesaFilter.getTipoDespesa())){
            query.where(qDespesa.tipoDespesa.id.eq(despesaFilter.getTipoDespesa().getId()));
        }
        if(Objects.nonNull(despesaFilter.getFornecedor())){
            query.where(qDespesa.fornecedor.id.eq(despesaFilter.getFornecedor().getId()));
        }

        if(Objects.nonNull(despesaFilter.getDataInicial()) && Objects.nonNull(despesaFilter.getDataFinal())){
            query.where(qDespesa.data.between(despesaFilter.getDataInicial(), despesaFilter.getDataFinal()));
        }else if(Objects.nonNull(despesaFilter.getDataInicial()) && Objects.isNull(despesaFilter.getDataFinal())){
            query.where(qDespesa.data.goe(despesaFilter.getDataInicial()));
        }else if(Objects.isNull(despesaFilter.getDataInicial()) && Objects.nonNull(despesaFilter.getDataFinal())){
            query.where(qDespesa.data.loe(despesaFilter.getDataFinal()));
        }
        if(Objects.nonNull(despesaFilter.getFormaPagamento())){
            query.where(qDespesa.formaPagamento.id.eq(despesaFilter.getFormaPagamento().getId()));
        }
        return query.fetch().get(0);
    }
}
