package com.br.vxassist.repository;

import com.br.vxassist.model.Extrato;
import com.br.vxassist.model.QExtrato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtratoRepository extends JpaRepository<Extrato, Long>,
        QuerydslPredicateExecutor<Extrato>, QuerydslBinderCustomizer<QExtrato> {

    @Override
    default public void customize(QuerydslBindings bindings, QExtrato root) {
    }
}
