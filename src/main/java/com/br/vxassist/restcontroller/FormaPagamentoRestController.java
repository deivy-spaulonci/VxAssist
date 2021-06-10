package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.FormaPagamentoDTO;
import com.br.vxassist.dto.FornecedorDTO;
import com.br.vxassist.exception.NotFoundException;
import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.serviceImpl.FormaPagamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<FormaPagamentoDTO> get(){
        return formaPagamentoServiceImpl.get();
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> save(@Valid @RequestBody FormaPagamentoDTO formaPagamentoDTO){
        return new ResponseEntity<>(this.formaPagamentoServiceImpl.save(formaPagamentoDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<FormaPagamentoDTO> update(@Valid @RequestBody FormaPagamentoDTO formaPagamentoDTO){
        try{
            return new ResponseEntity<>(this.formaPagamentoServiceImpl.save(this.formaPagamentoServiceImpl.findById(formaPagamentoDTO.getId())), HttpStatus.OK);
        }catch (Exception ex) {
            throw new NotFoundException("Id da Forma de Pagamento não encontrado!");
        }
    }

}
