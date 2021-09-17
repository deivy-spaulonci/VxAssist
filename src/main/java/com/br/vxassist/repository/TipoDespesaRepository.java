package com.br.vxassist.repository;

import com.br.vxassist.model.TipoDespesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDespesaRepository extends JpaRepository<TipoDespesa, Long>,
        QuerydslPredicateExecutor<TipoDespesa> {
}
