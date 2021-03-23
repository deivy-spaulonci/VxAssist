package com.br.vxassist.repository;

import com.br.vxassist.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>,
        QuerydslPredicateExecutor<Conta>{

}
