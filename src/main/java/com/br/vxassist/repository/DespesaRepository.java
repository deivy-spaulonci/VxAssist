package com.br.vxassist.repository;

import com.br.vxassist.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Despesa", path = "despesa")
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
