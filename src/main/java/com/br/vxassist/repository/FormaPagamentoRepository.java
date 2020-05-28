package com.br.vxassist.repository;

import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.projection.FormaPagamentoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = FormaPagamentoProjection.class, path = "forma-pagamento")
public interface FormaPagamentoRepository extends PagingAndSortingRepository<FormaPagamento, Long>, JpaRepository<FormaPagamento, Long> {
}
