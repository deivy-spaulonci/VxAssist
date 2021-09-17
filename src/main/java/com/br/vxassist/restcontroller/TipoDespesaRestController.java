package com.br.vxassist.restcontroller;

import com.br.vxassist.apidocs.TipoControllerDocs;
import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.dto.TipoDespesaDTO;
import com.br.vxassist.filter.TipoFilter;
import com.br.vxassist.serviceImpl.TipoDespesaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tipo-despesa")
public class TipoDespesaRestController implements TipoControllerDocs<TipoDespesaDTO>, Serializable{

    @Autowired
    private final TipoDespesaServiceImpl tipoDespesaServiceImpl;

    @Autowired
    public TipoDespesaRestController(TipoDespesaServiceImpl tipoDespesaServiceImpl){
        super();
        this.tipoDespesaServiceImpl = tipoDespesaServiceImpl;
    }

    @GetMapping()
    public List<TipoDespesaDTO> get(@ModelAttribute TipoFilter tipoFilter){
        return tipoDespesaServiceImpl.get(tipoFilter);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoDespesaDTO create(@RequestBody @Valid TipoDespesaDTO tipoDespesaDTO) {
        return tipoDespesaServiceImpl.create(tipoDespesaDTO);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody TipoDespesaDTO tipoDespesaDTO){
        try{
            this.tipoDespesaServiceImpl.update(tipoDespesaDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex) {
            throw new EntityNotFoundException("Id do Tipo de Despesa não encontrado!");
        }
    }
}
