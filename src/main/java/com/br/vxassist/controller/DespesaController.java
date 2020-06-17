package com.br.vxassist.controller;

import com.br.vxassist.model.Despesa;
import com.br.vxassist.serviceImpl.DespesaServiceImpl;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.io.Serializable;

@RestController
@RequestMapping("/api/despesa")
public class DespesaController implements Serializable {

    @Autowired
    private final DespesaServiceImpl despesaServiceImpl;
//
//    @GetMapping("/despesas")
//    public List<Despesa> get(@RequestParam(value = SORT_BY_PARAM, required = false, defaultValue = "data") final String sortProperty,
//                             @RequestParam(value = SORT_DIRECTION_PARAM, required = false, defaultValue = "asc") final String sortDirection,
//                             @RequestParam(value = PAGE_PARAM, required = false) final Long page){
//        return _despesaRepository.findAll();
//    }

    @Autowired
    public DespesaController(DespesaServiceImpl despesaServiceImpl){
        super();
        this.despesaServiceImpl = despesaServiceImpl;
    }

    @PostMapping("/")
    public ResponseEntity<Despesa> save(@Valid @RequestBody Despesa despesa){
        this.despesaServiceImpl.save(despesa);
        return new ResponseEntity<>(despesa, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Page<Despesa> get(@QuerydslPredicate(root = Despesa.class) Predicate despesa, Pageable pageable){
        return despesaServiceImpl.getAll(despesa, pageable);
    }

    @GetMapping("/total")
    public Long get(){
        return despesaServiceImpl.count();
    }

}
