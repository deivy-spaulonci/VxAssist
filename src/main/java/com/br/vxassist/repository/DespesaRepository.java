package com.br.vxassist.repository;

import com.br.vxassist.model.Despesa;
import com.br.vxassist.projection.DespesaProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(excerptProjection = DespesaProjection.class, path = "despesa")
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
//    @RestResource(path = "sortByTipoDespesaNome")
//    @Query("SELECT d FROM Despesa d ORDER BY d.tipoDespesa.nome ASC")
//      List<Despesa> findItemsLessThanAndType(Page page);


}
