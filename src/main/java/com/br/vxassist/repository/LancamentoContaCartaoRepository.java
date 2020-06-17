package com.br.vxassist.repository;

import com.br.vxassist.model.LancamentoContaCartao;
import com.br.vxassist.model.QLancamentoContaCartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface LancamentoContaCartaoRepository extends JpaRepository<LancamentoContaCartao, Long>,
        QuerydslPredicateExecutor<LancamentoContaCartao>, QuerydslBinderCustomizer<QLancamentoContaCartao> {
    
    @Override
    default public void customize(QuerydslBindings bindings, QLancamentoContaCartao root) {
    }
}

