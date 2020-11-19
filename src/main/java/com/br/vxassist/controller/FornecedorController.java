package com.br.vxassist.controller;

import com.br.vxassist.exception.NotFoundException;
import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.filter.FornecedorFilter;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.serviceImpl.FornecedorServiceImpl;
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
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController implements Serializable {

    @Autowired
    private final FornecedorServiceImpl fornecedorServiceImpl;

    public FornecedorController(FornecedorServiceImpl fornecedorServiceImpl){
        super();
        this.fornecedorServiceImpl = fornecedorServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<Page<Fornecedor>> listAll(@ModelAttribute FornecedorFilter fornecedorFilter,
                                                    Pageable pageable){
        return new ResponseEntity<>(fornecedorServiceImpl.getAll(fornecedorFilter, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> findFornecedorById(@PathVariable("id") Long id){
        try{
            Fornecedor fornecedor = fornecedorServiceImpl.findFornecedorById(id);
            return new ResponseEntity<>(fornecedor, HttpStatus.OK);
        }catch (Exception ex) {
            throw new NotFoundException("Id do Fornecedor não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<Fornecedor> save(@Valid @RequestBody Fornecedor fornecedor){
        return new ResponseEntity<>(this.fornecedorServiceImpl.save(fornecedor), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Fornecedor> update(@Valid @RequestBody Fornecedor fornecedor){
        try{
            Fornecedor fornec = this.fornecedorServiceImpl.findFornecedorById(fornecedor.getId());
            return new ResponseEntity<>(this.fornecedorServiceImpl.save(fornecedor), HttpStatus.OK);
        }catch (Exception ex) {
            throw new NotFoundException("Id do Fornecedor não encontrado!");
        }

    }

}
