package com.br.vxassist.restcontroller;


import com.br.vxassist.exception.NotFoundException;
import com.br.vxassist.filter.ContaFilter;
import com.br.vxassist.serviceImpl.ContaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import com.br.vxassist.dto.ContaDTO;

@RestController
@RequestMapping("/api/v1/conta")
public class ContaRestController implements Serializable {
    @Autowired
    private final ContaServiceImpl contaServiceImpl;

    @Autowired
    public ContaRestController(ContaServiceImpl contaServiceImpl){
        super();
        this.contaServiceImpl = contaServiceImpl;
    }

    @GetMapping()
    public List<ContaDTO> get(@ModelAttribute ContaFilter contaFitler, Sort sort){
        return this.contaServiceImpl.get(contaFitler, sort);
    }

    @GetMapping("/page")
    public Page<ContaDTO> getPage(@ModelAttribute ContaFilter contaFitler,
                                  Pageable pageable){
        Page<ContaDTO> resultPage = this.contaServiceImpl.getPage(contaFitler, pageable);
        return resultPage;
    }

    @PostMapping
    public ResponseEntity<ContaDTO> save(@Valid @RequestBody ContaDTO contaDTO){
        return new ResponseEntity<>(this.contaServiceImpl.save(contaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ContaDTO> update(@Valid @RequestBody ContaDTO contaDTO){
        try{
            return new ResponseEntity<>(this.contaServiceImpl.save(this.contaServiceImpl.findContaById(contaDTO.getId())), HttpStatus.OK);
        }catch (Exception ex) {
            throw new NotFoundException("Id da Conta não encontrado!");
        }
    }

    @GetMapping("/totalRegistros")
    public Long getTotalRegistros(){
        return contaServiceImpl.count();
    }

    @GetMapping("/total")
    public BigDecimal getTotal(@ModelAttribute ContaFilter contaFitler){
        return contaServiceImpl.total(contaFitler);
    }

    @GetMapping("/{id}")
    public ContaDTO findById(@PathVariable Long id){
        return this.contaServiceImpl.findContaById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.contaServiceImpl.findContaById(id);
        this.contaServiceImpl.excluirConta(id);
    }

}
