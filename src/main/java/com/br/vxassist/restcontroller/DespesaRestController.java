package com.br.vxassist.restcontroller;

import com.br.vxassist.dto.DespesaDTO;
import com.br.vxassist.exception.NotFoundException;
import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.serviceImpl.DespesaServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@RestController
@RequestMapping("/api/v1/despesa")
public class DespesaRestController implements Serializable {

    @Autowired
    private final DespesaServiceImpl despesaServiceImpl;
//
//    @GetMapping("/despesas")
//    public List<Despesa> get(@RequestParam(value = SORT_BY_PARAM, required = false, defaultValue = "data") final String sortProperty,
//                             @RequestParam(value = SORT_DIRECTION_PARAM, required = false, defaultValue = "asc") final String sortDirection,
//                             @RequestParam(value = PAGE_PARAM, required = false) final Long page){
//        return _despesaRepository.findAll();
//    }

    @Autowired
    public DespesaRestController(DespesaServiceImpl despesaServiceImpl){
        super();
        this.despesaServiceImpl = despesaServiceImpl;
    }

    @GetMapping
    public List<DespesaDTO> get(@ModelAttribute DespesaFilter despesaFilter, Sort sort){
        return despesaServiceImpl.get(despesaFilter, sort);
    }

    @ApiOperation("Paginação de desepsas")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success method return")
    })
    @GetMapping("/page")
    public Page<DespesaDTO> getPage(@ModelAttribute DespesaFilter despesaFilter,
                             Pageable pageable){
        Page<DespesaDTO> resultPage = despesaServiceImpl.getPage(despesaFilter, pageable);
        return resultPage;
    }

    @GetMapping("/totalRegistros")
    public Long getTotalRegistros(@ModelAttribute DespesaFilter despesaFilter){
        return despesaServiceImpl.count();
    }

    @GetMapping("/total")
    public BigDecimal getTotal(@ModelAttribute DespesaFilter despesaFilter){
        return despesaServiceImpl.total(despesaFilter);
    }

    @GetMapping("/{id}")
    public DespesaDTO findById(@PathVariable Long id){
//        return new ResponseEntity<DespesaDTO>();
        return this.despesaServiceImpl.findById(id);
    }

    @PostMapping
    public ResponseEntity<DespesaDTO> save(@Valid @RequestBody DespesaDTO despesaDTO){
        return new ResponseEntity<>(this.despesaServiceImpl.save(despesaDTO), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<DespesaDTO> update(@Valid @RequestBody DespesaDTO despesaDTO){
        try{
            return new ResponseEntity<>(this.despesaServiceImpl.save(this.despesaServiceImpl.findById(despesaDTO.getId())), HttpStatus.OK);
        }catch (Exception ex) {
            throw new NotFoundException("Id do Despesa não encontrado!");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        this.despesaServiceImpl.findById(id);
        this.despesaServiceImpl.excluir(id);
    }
}
