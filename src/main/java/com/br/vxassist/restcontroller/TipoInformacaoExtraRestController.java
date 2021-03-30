package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.TipoInformacaoExtraDTO;
import com.br.vxassist.model.TipoInformacaoExtra;
import com.br.vxassist.serviceImpl.TipoInformacaoExtraServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo-informacao-extra")
public class TipoInformacaoExtraRestController implements Serializable {
    @Autowired
    private final TipoInformacaoExtraServiceImpl tipoInformacaoExtraServiceImpl;

    @Autowired
    public TipoInformacaoExtraRestController(TipoInformacaoExtraServiceImpl tipoInformacaoExtraServiceImpl){
        super();
        this.tipoInformacaoExtraServiceImpl = tipoInformacaoExtraServiceImpl;
    }

    @GetMapping()
    public List<TipoInformacaoExtraDTO> getSelect(){
        return tipoInformacaoExtraServiceImpl.get();
    }

}
