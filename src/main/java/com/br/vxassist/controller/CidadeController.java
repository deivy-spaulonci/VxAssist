package com.br.vxassist.controller;

import com.br.vxassist.filter.CidadeFilter;
import com.br.vxassist.model.Cidade;
import com.br.vxassist.serviceImpl.CidadeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController implements Serializable {

    @Autowired
    private final CidadeServiceImpl cidadeServiceImpl;

    @Autowired
    public CidadeController(CidadeServiceImpl cidadeServiceImpl){
        super();
        this.cidadeServiceImpl = cidadeServiceImpl;
    }

    @GetMapping()
    public Page<Cidade> get(@ModelAttribute CidadeFilter cidadeFitler,
                            Pageable pageable){
        Page<Cidade> resultPage = this.cidadeServiceImpl.getAll(cidadeFitler, pageable);
        return resultPage;
    }

    @GetMapping("/{id}")
    public Cidade findById(@PathVariable Long id){
        return this.cidadeServiceImpl.findCidadeById(id);
    }


}
