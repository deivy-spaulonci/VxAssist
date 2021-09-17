package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.TipoContaDTO;
import com.br.vxassist.filter.TipoFilter;
import com.br.vxassist.serviceImpl.TipoContaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
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
    public List<TipoContaDTO> get(@ModelAttribute TipoFilter tipoFilter){
        return tipoContaServiceImpl.get(tipoFilter);
    }

    @PostMapping
    public ResponseEntity<TipoContaDTO> save(@Valid @RequestBody TipoContaDTO tipoContaDTO){
        return new ResponseEntity<>(this.tipoContaServiceImpl.create(tipoContaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TipoContaDTO> update(@Valid @RequestBody TipoContaDTO tipoContaDTO){
        try{
            return new ResponseEntity<>(this.tipoContaServiceImpl.create(this.tipoContaServiceImpl.findById(tipoContaDTO.getId())), HttpStatus.OK);
        }catch (Exception ex) {
            throw new EntityNotFoundException("Id do Tipo de Conta não encontrado!");
        }
    }
}
