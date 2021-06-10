package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.dto.TipoContaDTO;
import com.br.vxassist.exception.NotFoundException;
import com.br.vxassist.serviceImpl.TipoContaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<TipoContaDTO> save(@Valid @RequestBody TipoContaDTO tipoContaDTO){
        return new ResponseEntity<>(this.tipoContaServiceImpl.save(tipoContaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TipoContaDTO> update(@Valid @RequestBody TipoContaDTO tipoContaDTO){
        try{
            return new ResponseEntity<>(this.tipoContaServiceImpl.save(this.tipoContaServiceImpl.findById(tipoContaDTO.getId())), HttpStatus.OK);
        }catch (Exception ex) {
            throw new NotFoundException("Id do Tipo de Conta não encontrado!");
        }
    }
}
