package com.br.vxassist.controller;

import com.br.vxassist.model.Despesa;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.serviceImpl.FornecedorServiceImpl;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController implements Serializable {

    @Autowired
    private final FornecedorServiceImpl fornecedorServiceImpl;

    public FornecedorController(FornecedorServiceImpl fornecedorServiceImpl){
        super();
        this.fornecedorServiceImpl = fornecedorServiceImpl;
    }

    @GetMapping("/all")
    public Page<Fornecedor> get(@QuerydslPredicate(root = Fornecedor.class) Predicate fornecedor, Pageable pageable){
        return fornecedorServiceImpl.getAll(fornecedor, pageable);
    }

    @GetMapping("/allselect")
    public List<Fornecedor> getAllSelect(Predicate fornecedor, Pageable pageable){
        return fornecedorServiceImpl.getAll(fornecedor, pageable).getContent();
    }

}
