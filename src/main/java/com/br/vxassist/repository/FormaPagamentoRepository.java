package com.br.vxassist.repository;

import com.br.vxassist.model.FormaPagamento;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "FormaPagamento", path = "formaPagamento")
public interface FormaPagamentoRepository extends PagingAndSortingRepository<FormaPagamento, Long> {
}
