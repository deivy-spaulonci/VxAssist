package com.br.vxassist.repository;

import com.br.vxassist.model.InformacaoExtra;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "InformacaoExtra", path = "informacaoExtra")
public interface InformacaoExtraRepository extends PagingAndSortingRepository<InformacaoExtra, Long> {
}

