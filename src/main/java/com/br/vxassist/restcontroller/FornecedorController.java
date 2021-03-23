package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.FornecedorDTO;
import com.br.vxassist.exception.NotFoundException;
import com.br.vxassist.filter.FornecedorFilter;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.serviceImpl.FornecedorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorController implements Serializable {

    @Autowired
    private final FornecedorServiceImpl fornecedorServiceImpl;

    public FornecedorController(FornecedorServiceImpl fornecedorServiceImpl){
        super();
        this.fornecedorServiceImpl = fornecedorServiceImpl;
    }

    @GetMapping()
    public List<FornecedorDTO> get(@ModelAttribute FornecedorFilter fornecedorFilter, Sort sort){
        return fornecedorServiceImpl.get(fornecedorFilter, sort);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<FornecedorDTO>> listAll(@ModelAttribute FornecedorFilter fornecedorFilter,
                                                       Pageable pageable){
        return new ResponseEntity<>(fornecedorServiceImpl.getPage(fornecedorFilter, pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FornecedorDTO> findFornecedorById(@PathVariable("id") Long id){
        try{
            FornecedorDTO fornecedorDTO = fornecedorServiceImpl.findFornecedorById(id);
            return new ResponseEntity<>(fornecedorDTO, HttpStatus.OK);
        }catch (Exception ex) {
            throw new NotFoundException("Id do Fornecedor não encontrado!");
        }
    }

    @PostMapping
    public ResponseEntity<FornecedorDTO> save(@Valid @RequestBody FornecedorDTO fornecedorDTO){
        return new ResponseEntity<>(this.fornecedorServiceImpl.save(fornecedorDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<FornecedorDTO> update(@Valid @RequestBody Fornecedor fornecedor){
        try{
            FornecedorDTO fornecedorDTO = this.fornecedorServiceImpl.findFornecedorById(fornecedor.getId());
            return new ResponseEntity<>(this.fornecedorServiceImpl.save(fornecedorDTO), HttpStatus.OK);
        }catch (Exception ex) {
            throw new NotFoundException("Id do Fornecedor não encontrado!");
        }

    }

}
