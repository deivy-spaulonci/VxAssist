package com.br.vxassist.controller;

import com.br.vxassist.filter.ContaFilter;
import com.br.vxassist.model.Conta;
import com.br.vxassist.serviceImpl.ContaServiceImpl;
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
@RequestMapping("/api/conta")
public class ContaController implements Serializable {
    @Autowired
    private final ContaServiceImpl contaServiceImpl;

    @Autowired
    public ContaController(ContaServiceImpl contaServiceImpl){
        super();
        this.contaServiceImpl = contaServiceImpl;
    }

    @PostMapping
    public ResponseEntity<Conta> save(@Valid @RequestBody Conta conta){
        this.contaServiceImpl.save(conta);
        return new ResponseEntity<>(conta, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public Page<Conta> get(@ModelAttribute ContaFilter contaFitler,
                           Pageable pageable){
        Page<Conta> resultPage = this.contaServiceImpl.getAll(contaFitler, pageable);
        return resultPage;
    }

    @GetMapping("/totalRegistros")
    public Long getTotalRegistros(){
        return contaServiceImpl.count();
    }

    @GetMapping("/total")
    public BigDecimal getTotal(@ModelAttribute ContaFilter contaFitler){
        return contaServiceImpl.total(contaFitler);
    }

    @GetMapping("/{id}")
    public Conta findById(@PathVariable Long id){
        return this.contaServiceImpl.findContaById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.contaServiceImpl.findContaById(id);
        this.contaServiceImpl.excluirConta(id);
    }

}
