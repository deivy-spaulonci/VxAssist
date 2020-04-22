package com.br.vxassist.repository;

import com.br.vxassist.model.Fornecedor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Fornecedor", path = "fornecedor")
public interface FornecedorRepository extends PagingAndSortingRepository<Fornecedor, Long> {
}

