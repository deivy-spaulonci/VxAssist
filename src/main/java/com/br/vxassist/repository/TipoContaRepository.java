package com.br.vxassist.repository;

import com.br.vxassist.model.QTipoConta;
import com.br.vxassist.model.TipoConta;
import com.br.vxassist.model.TipoInformacaoExtra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoContaRepository extends JpaRepository<TipoConta, Long>,
        QuerydslPredicateExecutor<TipoConta>, QuerydslBinderCustomizer<QTipoConta> {

    @Override
    default public void customize(QuerydslBindings bindings, QTipoConta root) {
    }

    Optional<TipoConta> findByNome(String nome);
}
