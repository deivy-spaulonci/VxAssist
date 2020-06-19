package com.br.vxassist.controller;

import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.model.TipoInformacaoExtra;
import com.br.vxassist.serviceImpl.TipoDespesaServiceImpl;
import com.br.vxassist.serviceImpl.TipoInformacaoExtraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/tipo-informacao-extra")
public class TipoInformacaoExtraController implements Serializable {
    @Autowired
    private final TipoInformacaoExtraServiceImpl tipoInformacaoExtraServiceImpl;

    @Autowired
    public TipoInformacaoExtraController(TipoInformacaoExtraServiceImpl tipoInformacaoExtraServiceImpl){
        super();
        this.tipoInformacaoExtraServiceImpl = tipoInformacaoExtraServiceImpl;
    }

    @GetMapping("/allSelect")
    public List<TipoInformacaoExtra> getSelect(){
        return tipoInformacaoExtraServiceImpl.getAllSelect();
    }

}
