package com.br.vxassist.repository;

import com.br.vxassist.model.ExtratoDescricao;
import com.br.vxassist.model.QExtratoDescricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtratoDescricaoRepository extends JpaRepository<ExtratoDescricao, Long>,
        QuerydslPredicateExecutor<ExtratoDescricao>, QuerydslBinderCustomizer<QExtratoDescricao> {

    @Override
    default public void customize(QuerydslBindings bindings, QExtratoDescricao root) {
    }
}
