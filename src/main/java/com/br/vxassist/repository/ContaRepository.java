package com.br.vxassist.repository;

import com.br.vxassist.model.Conta;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Conta", path = "conta")
public interface ContaRepository extends PagingAndSortingRepository<Conta, Long> {
}
