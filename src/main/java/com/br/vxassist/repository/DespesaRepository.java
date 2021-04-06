package com.br.vxassist.repository;

import com.br.vxassist.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long>,
        QuerydslPredicateExecutor<Despesa> {

//    @Query("SELECT d FROM Despesa d WHERE c.movie = :movie")
//    List<Despesa> findByMovieCustom(@Param("movie") String movieName, Pageable pageable);

//    @Query("FROM Customer c " +
//            "WHERE LOWER(c.name) like %:searchTerm% " +
//            "OR LOWER(c.email) like %:searchTerm%")
//    Page<Customer> search(
//            @Param("searchTerm") String searchTerm,
//            Pageable pageable);



}
