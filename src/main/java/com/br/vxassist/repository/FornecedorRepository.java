package com.br.vxassist.repository;

import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.projection.FornecedorProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = FornecedorProjection.class, path = "fornecedor")
public interface FornecedorRepository extends PagingAndSortingRepository<Fornecedor, Long>, JpaRepository<Fornecedor, Long> {
}

