package com.br.vxassist.repository;

import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.QFormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>,
        QuerydslPredicateExecutor<FormaPagamento>, QuerydslBinderCustomizer<QFormaPagamento> {

    @Override
    default public void customize(QuerydslBindings bindings, QFormaPagamento root) {
    }
}
