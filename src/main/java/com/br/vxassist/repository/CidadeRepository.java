package com.br.vxassist.repository;

import com.br.vxassist.model.Cidade;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "Cidade", path = "cidade")
public interface CidadeRepository extends PagingAndSortingRepository<Cidade, Long> {
}
