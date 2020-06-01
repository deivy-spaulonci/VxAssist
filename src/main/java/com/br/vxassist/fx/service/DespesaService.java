package com.br.vxassist.fx.service;

import com.br.vxassist.model.Despesa;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class DespesaService {
    @Autowired
    DespesaRepository despesaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Despesa> listaDespesas(TipoDespesa tipoDespesa,
                                       Fornecedor fornecedor,
                                       Date inicio,
                                       Date termino,
                                       FormaPagamento formaPagamento){

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Despesa> query = cb.createQuery(Despesa.class);
        Root<Despesa> root = query.from(Despesa.class);
        if(Objects.nonNull(tipoDespesa)){
            query.where(cb.equal(root.get("tipoDespesa"), tipoDespesa));
        }

        if(Objects.nonNull(fornecedor)){
            query.where(cb.equal(root.get("fornecedor"), fornecedor));
        }

        if(Objects.nonNull(inicio) && Objects.nonNull(termino)){
            query.where(cb.between(root.get("data"), inicio, termino));
        }else if(Objects.nonNull(inicio)){
            query.where(cb.greaterThanOrEqualTo(root.get("data"), inicio));
        }else if(Objects.nonNull(termino)){
            query.where(cb.greaterThanOrEqualTo(root.get("data"), termino));
        }

        if(Objects.nonNull(formaPagamento)){
            query.where(cb.equal(root.get("formaPagamento"), formaPagamento));
        }
        return entityManager.createQuery(query).getResultList();
    }
}
