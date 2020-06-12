package com.br.vxassist.service;

import com.br.vxassist.model.Despesa;
import com.br.vxassist.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class DespesaServiceImpl implements DespesaService{

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DespesaRepository despesaRepository;

    @Override
    public Page<Despesa> getAll(Despesa despesa, Pageable pageable){

        despesaRepository.findAll()

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Despesa> query = builder.createQuery(Despesa.class);
        Root<Despesa> root = query.from(Despesa.class);

        Predicate isComedy = builder.equal(root.get("formaPagamento").get("nome"), despesa.getFormaPagamento().getNome());
        //Predicate isReallyOld = builder.lessThan(root.get(Movie.createdAt), today.minusYears(25));
        query.where(builder.and(isComedy));
        List<Despesa> lista = entityManager.createQuery(query.select(root)).getResultList();

        Page<Despesa> page = new PageImpl<Despesa>(lista, pageable, pageable.getPageSize());

        return page;
    }

}
