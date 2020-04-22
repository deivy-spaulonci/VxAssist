package com.br.vxassist.repository;

import com.br.vxassist.model.ExtratoDescricao;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "ExtratoDescricao", path = "extratoDescricao")
public interface ExtratoDescricaoRepository extends PagingAndSortingRepository<ExtratoDescricao, Long> {
}
