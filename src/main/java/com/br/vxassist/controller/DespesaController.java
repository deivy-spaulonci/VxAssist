package com.br.vxassist.controller;

import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.model.Despesa;
import com.br.vxassist.serviceImpl.DespesaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.math.BigDecimal;

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

    @PostMapping
    public ResponseEntity<Despesa> save(@Valid @RequestBody Despesa despesa){
        this.despesaServiceImpl.save(despesa);
        return new ResponseEntity<>(despesa, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Page<Despesa> get(@ModelAttribute DespesaFilter despesaFilter,
                             Pageable pageable){
        Page<Despesa> resultPage = despesaServiceImpl.getAll(despesaFilter, pageable);
        return resultPage;
    }

    @GetMapping("/totalRegistros")
    public Long getTotalRegistros(){
        return despesaServiceImpl.count();
    }

    @GetMapping("/total")
    public BigDecimal getTotal(@ModelAttribute DespesaFilter despesaFilter){
        return despesaServiceImpl.total(despesaFilter);
    }

    @GetMapping("/{id}")
    public Despesa findById(@PathVariable Long id){
        return this.despesaServiceImpl.findDespesaById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.despesaServiceImpl.findDespesaById(id);
        this.despesaServiceImpl.excluirDespesa(id);
    }
}
