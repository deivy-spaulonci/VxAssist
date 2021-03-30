package com.br.vxassist.controller;

import com.br.vxassist.dto.DespesaDTO;
import com.br.vxassist.filter.DespesaFilter;
import com.br.vxassist.model.Fornecedor;
import com.br.vxassist.serviceImpl.DespesaServiceImpl;
import com.br.vxassist.serviceImpl.FormaPagamentoServiceImpl;
import com.br.vxassist.serviceImpl.FornecedorServiceImpl;
import com.br.vxassist.serviceImpl.TipoDespesaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class DespesaController {

    @Autowired
    private final DespesaServiceImpl despesaServiceImpl;
    @Autowired
    private final TipoDespesaServiceImpl tipoDespesaServiceImpl;
    @Autowired
    private final FornecedorServiceImpl fornecedorServiceImpl;
    @Autowired
    private final FormaPagamentoServiceImpl formaPagamentoServiceImpl;


    public DespesaController(DespesaServiceImpl despesaServiceImpl,
                             TipoDespesaServiceImpl tipoDespesaServiceImpl,
                             FornecedorServiceImpl fornecedorServiceImpl,
                             FormaPagamentoServiceImpl formaPagamentoServiceImpl) {
        this.despesaServiceImpl = despesaServiceImpl;
        this.tipoDespesaServiceImpl = tipoDespesaServiceImpl;
        this.fornecedorServiceImpl = fornecedorServiceImpl;
        this.formaPagamentoServiceImpl = formaPagamentoServiceImpl;
    }

    @GetMapping("/despesa")
    public String consulta(Model model, DespesaFilter despesaFilter, Pageable pageable) {

        List<Fornecedor> fornecedores = fornecedorServiceImpl.getSelect(null);
        Page<DespesaDTO> retorno = despesaServiceImpl.getPage(despesaFilter, pageable);


        model.addAttribute("despesaPage", retorno);
        model.addAttribute("totalValor", despesaServiceImpl.total(despesaFilter));
        model.addAttribute("tipos", tipoDespesaServiceImpl.get());
        model.addAttribute("formas", formaPagamentoServiceImpl.get());
//        model.addAttribute("fornecedores", fornecedores);
        model.addAttribute("despesaFilter", despesaFilter);
        return "despesa-list";
    }
}
