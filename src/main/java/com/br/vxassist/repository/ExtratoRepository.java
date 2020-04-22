package com.br.vxassist.repository;

import com.br.vxassist.model.Extrato;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Extrato", path = "extrato")
public interface ExtratoRepository extends PagingAndSortingRepository<Extrato, Long> {
}
