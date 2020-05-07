package com.br.vxassist.repository;

import com.br.vxassist.model.LancamentoContaCartao;
import com.br.vxassist.projection.LancamentoContaCartaoProjection;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = LancamentoContaCartaoProjection.class, path = "lancamento-conta-cartao")
public interface LancamentoContaCartaoRepository extends PagingAndSortingRepository<LancamentoContaCartao, Long> {
}

