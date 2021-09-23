package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.filter.TipoFilter;
import com.br.vxassist.serviceImpl.FormaPagamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/forma-pagamento")
public class FormaPagamentoRestController implements Serializable {


    @Autowired
    private final FormaPagamentoServiceImpl formaPagamentoServiceImpl;

    @Autowired
    public FormaPagamentoRestController(FormaPagamentoServiceImpl formaPagamentoServiceImpl){
        super();
        this.formaPagamentoServiceImpl = formaPagamentoServiceImpl;
    }

    @GetMapping()
    public List<FormaPagamentoDTO> get(@ModelAttribute TipoFilter tipoFilter){
        return formaPagamentoServiceImpl.get(tipoFilter, Sort.by("nome").ascending());
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> save(@Valid @RequestBody FormaPagamentoDTO formaPagamentoDTO){
        return new ResponseEntity<>(this.formaPagamentoServiceImpl.create(formaPagamentoDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> update(@Valid @RequestBody FormaPagamentoDTO formaPagamentoDTO){
        try{
            this.formaPagamentoServiceImpl.update(formaPagamentoDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex) {
            throw new EntityNotFoundException("Id da Forma de Pagamento não encontrado!");
        }
    }

}
