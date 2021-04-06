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
@RequestMapping("/api/v1/tipo-despesa")
public class TipoDespesaRestController implements Serializable {

    @Autowired
    private final TipoDespesaServiceImpl tipoDespesaServiceImpl;

    @Autowired
    public TipoDespesaRestController(TipoDespesaServiceImpl tipoDespesaServiceImpl){
        super();
        this.tipoDespesaServiceImpl = tipoDespesaServiceImpl;
    }

    @GetMapping()
    public List<TipoDespesaDTO> get(Pageable pageable){
        return tipoDespesaServiceImpl.get();
    }

}
