package com.br.vxassist.controller;

import com.br.vxassist.model.Despesa;
import com.br.vxassist.repository.DespesaRepository;
import com.br.vxassist.service.DespesaService;
import com.br.vxassist.service.DespesaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import javax.ws.rs.QueryParam;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import static com.br.vxassist.util.Hateoas.*;

@RestController
@RequestMapping("/api/despesa")
public class DespesaController implements Serializable {

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
    public DespesaController(DespesaServiceImpl despesaServiceImpl){
        super();
        this.despesaServiceImpl = despesaServiceImpl;
    }

    @GetMapping("/all")
    public Page<Despesa> get(@ModelAttribute Despesa despesa, Pageable pageable){
        System.out.println(despesa.getFormaPagamento().getNome());
        return despesaServiceImpl.getAll(despesa, pageable);
    }
}
