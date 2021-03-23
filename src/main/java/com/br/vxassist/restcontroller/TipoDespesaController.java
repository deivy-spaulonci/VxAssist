package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.TipoDespesaDTO;
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

    @GetMapping()
    public List<TipoDespesaDTO> getSelect(Pageable pageable){
        return tipoDespesaServiceImpl.get();
    }

}