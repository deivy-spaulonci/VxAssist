package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.dto.TipoDespesaDTO;
import com.br.vxassist.exception.NotFoundException;
import com.br.vxassist.model.TipoDespesa;
import com.br.vxassist.serviceImpl.TipoDespesaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping
    public ResponseEntity<TipoDespesaDTO> save(@Valid @RequestBody TipoDespesaDTO tipoDespesaDTO){
        return new ResponseEntity<>(this.tipoDespesaServiceImpl.save(tipoDespesaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TipoDespesaDTO> update(@Valid @RequestBody TipoDespesaDTO tipoDespesaDTO){
        try{
            return new ResponseEntity<>(this.tipoDespesaServiceImpl.save(this.tipoDespesaServiceImpl.findTipoDespesaById(tipoDespesaDTO.getId())), HttpStatus.OK);
        }catch (Exception ex) {
            throw new NotFoundException("Id do Tipo de Despesa não encontrado!");
        }

    }
}
