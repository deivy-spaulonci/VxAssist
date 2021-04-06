package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.TipoContaDTO;
import com.br.vxassist.serviceImpl.TipoContaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo-conta")
public class TipoContaRestController  implements Serializable {

    @Autowired
    private final TipoContaServiceImpl tipoContaServiceImpl;

    @Autowired
    public TipoContaRestController(TipoContaServiceImpl tipoContaServiceImpl) {
        super();
        this.tipoContaServiceImpl = tipoContaServiceImpl;
    }

    @GetMapping()
    public List<TipoContaDTO> get(Pageable pageable){
        return tipoContaServiceImpl.get();
    }
}
