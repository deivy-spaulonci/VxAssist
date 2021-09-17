package com.br.vxassist.repository;

import com.br.vxassist.model.InformacaoExtra;
import com.br.vxassist.model.QInformacaoExtra;
import com.br.vxassist.model.TipoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InformacaoExtraRepository extends JpaRepository<InformacaoExtra, Long>,
        QuerydslPredicateExecutor<InformacaoExtra>, QuerydslBinderCustomizer<QInformacaoExtra> {

    @Override
    default public void customize(QuerydslBindings bindings, QInformacaoExtra root) {
    }

}

