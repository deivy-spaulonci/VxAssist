package com.br.vxassist.controller;

import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.serviceImpl.TipoDespesaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/tipo-despesa")
public class TipoDespesaController implements Serializable {

    @Autowired
    private final TipoDespesaServiceImpl tipoDespesaServiceImpl;

    @Autowired
    public TipoDespesaController(TipoDespesaServiceImpl tipoDespesaServiceImpl){
        super();
        this.tipoDespesaServiceImpl = tipoDespesaServiceImpl;
    }

    @GetMapping("/allSelect")
    public List<TipoDespesa> getSelect(Pageable pageable){
        return tipoDespesaServiceImpl.getAllSelect();
    }

}
