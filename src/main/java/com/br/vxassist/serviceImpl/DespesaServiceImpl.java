package com.br.vxassist.serviceImpl;

import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.model.Despesa;
import com.br.vxassist.model.QDespesa;
import com.br.vxassist.repository.DespesaRepository;
import com.br.vxassist.service.DespesaService;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class DespesaServiceImpl implements DespesaService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DespesaRepository despesaRepository;

    @Override
    public Page<Despesa> getAll(Predicate predicate, Pageable pageable){

//
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Despesa> query = builder.createQuery(Despesa.class);
//        Root<Despesa> root = query.from(Despesa.class);
//
//        Predicate isComedy = builder.equal(root.get("formaPagamento").get("nome"), despesa.getFormaPagamento().getNome());
//        //Predicate isReallyOld = builder.lessThan(root.get(Movie.createdAt), today.minusYears(25));
//        query.where(builder.and(isComedy));
//        List<Despesa> lista = entityManager.createQuery(query.select(root)).getResultList();
//
//        Page<Despesa> page = new PageImpl<Despesa>(lista, pageable, pageable.getPageSize());


        return despesaRepository.findAll(predicate, pageable);
    }

    @Override
    public Despesa save(Despesa despesa){
        return despesaRepository.save(despesa);
    }

    @Override
    public Long count() {
        return despesaRepository.count();
    }
//
//    @Override
//    public BigDecimal total() {
//        JPAQuery<Despesa> query = new JPAQuery<Despesa>(this.entityManager);
//        QDespesa qDespesa = QDespesa.despesa;
//        return query.select(qDespesa.valor.sum()).from(qDespesa).fetch().get(0);
//    }

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
        despesaFilter.
//        if(Objects.nonNull(despesaFilter.getData().Inicio()) && Objects.nonNull(despesaFilter.getDataFinal())){
//            query.where(qDespesa.data.between(despesaFilter.getDataInicio(), despesaFilter.getDataFinal()));
//        }else if(Objects.nonNull(despesaFilter.getDataInicio()) && Objects.isNull(despesaFilter.getDataFinal())){
//            query.where(qDespesa.data.eq(despesaFilter.getDataInicio()).and(qDespesa.data.after(despesaFilter.getDataInicio())));
//        }else if(Objects.isNull(despesaFilter.getDataInicio()) && Objects.nonNull(despesaFilter.getDataFinal())){
//            query.where(qDespesa.data.eq(despesaFilter.getDataFinal()).and(qDespesa.data.before(despesaFilter.getDataFinal())));
//        }
        if(Objects.nonNull(despesaFilter.getFormaPagamento())){
            query.where(qDespesa.formaPagamento.id.eq(despesaFilter.getFormaPagamento().getId()));
        }
        return query.fetch().get(0);
    }
}
