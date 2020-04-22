package com.br.vxassist.repository;

import com.br.vxassist.model.LancamentoContaCartao;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "LancamentoContaCartao", path = "lancamentoContaCartao")
public interface LancamentoContaCartaoRepository extends PagingAndSortingRepository<LancamentoContaCartao, Long> {
}

