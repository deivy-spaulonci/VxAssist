package com.br.vxassist.repository;

import com.br.vxassist.model.Conta;
import com.br.vxassist.projection.ContaProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = ContaProjection.class, path = "conta")
public interface ContaRepository extends PagingAndSortingRepository<Conta, Long> {
}
