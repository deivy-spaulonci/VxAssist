package com.br.vxassist.repository;

import com.br.vxassist.model.QTipoInformacaoExtra;
import com.br.vxassist.model.TipoInformacaoExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoInformacaoExtraRepository extends JpaRepository<TipoInformacaoExtra, Long>,
        QuerydslPredicateExecutor<TipoInformacaoExtra>, QuerydslBinderCustomizer<QTipoInformacaoExtra> {

    @Override
    default public void customize(QuerydslBindings bindings, QTipoInformacaoExtra root) {
    }
}

