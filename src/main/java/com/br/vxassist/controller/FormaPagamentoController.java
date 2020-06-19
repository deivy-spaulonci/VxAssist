package com.br.vxassist.controller;

import com.br.vxassist.model.FormaPagamento;
import com.br.vxassist.serviceImpl.FormaPagamentoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/forma-pagamento")
public class FormaPagamentoController implements Serializable {


    @Autowired
    private final FormaPagamentoServiceImpl formaPagamentoServiceImpl;

    @Autowired
    public FormaPagamentoController(FormaPagamentoServiceImpl formaPagamentoServiceImpl){
        super();
        this.formaPagamentoServiceImpl = formaPagamentoServiceImpl;
    }

    @GetMapping("/allSelect")
    public List<FormaPagamento> getSelect(){
        return formaPagamentoServiceImpl.getAllSelect();
    }

}
