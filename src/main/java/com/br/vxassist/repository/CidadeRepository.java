package com.br.vxassist.repository;

import com.br.vxassist.model.Cidade;
import com.br.vxassist.model.QCidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;


@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>,
        QuerydslPredicateExecutor<Cidade>, QuerydslBinderCustomizer<QCidade> {

    @Override
    default public void customize(QuerydslBindings bindings, QCidade root) {
    }

}
